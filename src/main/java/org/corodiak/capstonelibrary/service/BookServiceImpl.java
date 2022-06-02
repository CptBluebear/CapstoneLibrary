package org.corodiak.capstonelibrary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.corodiak.capstonelibrary.util.CodeGenerator;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final BookLogService bookLogService;
	private final CodeGenerator codeGenerator;

	@Override
	public List<BookVo> findAll(long start, long display) {
		List<Book> bookList = bookRepository.findAll(start, display);
		List<BookVo> results = bookList.stream()
			.map(e -> new BookVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public BookVo findBook(long seq) {
		Optional<Book> book = bookRepository.findBySeq(seq);
		if (book.isPresent()) {
			return new BookVo(book.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public boolean addBook(String title, String author, String publisher, String isbn, String thumbnail,
		LocalDate publishDate, String description, Category category, Long userSeq, Long groupSeq) {
		Book book = Book.builder()
			.title(title)
			.author(author)
			.publisher(publisher)
			.isbn(isbn)
			.thumbnail(thumbnail)
			.publishDate(publishDate)
			.description(description)
			.category(category)
			.user(User.builder().seq(userSeq).build())
			.group(Group.builder().seq(groupSeq).build())
			.build();
		book = bookRepository.save(book);
		book.setCode(codeGenerator.generate(Long.toString(book.getGroup().getSeq()), Long.toString(book.getSeq())));
		return true;
	}

	@Override
	public List<BookVo> findByUserSeq(long seq, long start, long display) {
		List<Book> bookList = bookRepository.findByUserSeq(seq, start, display);
		List<BookVo> results = bookList.stream()
			.map(e -> new BookVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookVo> findByGroupSeq(long seq, long start, long display) {
		List<Book> bookList = bookRepository.findByGroupSeq(seq, start, display);
		List<BookVo> results = bookList.stream()
			.map(e -> new BookVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public boolean removeBook(long seq) {
		long result = bookRepository.deleteBySeq(seq);
		return result == 1;
	}

	@Override
	@Transactional
	public boolean updateBook(long seq, String title, String author, String publisher, String isbn, String thumbnail,
		LocalDate publishDate, String description, Category category) {

		Optional<Book> book = bookRepository.findBySeq(seq);
		if (!book.isPresent()) {
			throw new SearchResultNotExistException();
		}

		Book result = book.get();
		result.setTitle(title);
		result.setAuthor(author);
		result.setPublisher(publisher);
		result.setIsbn(isbn);
		result.setThumbnail(thumbnail);
		result.setPublishDate(publishDate);
		result.setDescription(description);
		result.setCategory(category);

		return true;
	}

	@Override
	public boolean borrowBook(Long userSeq, Long bookSeq) {
		Optional<Book> book = bookRepository.findBySeq(bookSeq);
		if (!book.isPresent()) {
			throw new SearchResultNotExistException();
		}

		long result = bookRepository.borrowBook(bookSeq);
		bookLogService.addBookLog(BookLogStatus.BORROW, userSeq, bookSeq, book.get().getGroup().getSeq());
		return result == 1;
	}

	@Override
	public boolean returnBook(Long userSeq, Long bookSeq) {
		Optional<Book> book = bookRepository.findBySeq(bookSeq);
		if (!book.isPresent()) {
			throw new SearchResultNotExistException();
		}

		long result = bookRepository.returnBook(bookSeq);
		bookLogService.addBookLog(BookLogStatus.RETURN, userSeq, bookSeq, book.get().getGroup().getSeq());
		return result == 1;
	}

	@Override
	public List<BookVo> searchBook(String keyword, String category) {
		List<Book> bookList = bookRepository.search(keyword, category);
		List<BookVo> results = bookList.stream()
			.map(e -> new BookVo(e))
			.collect(Collectors.toList());
		return results;
	}
}
