package org.corodiak.capstonelibrary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public List<BookVo> findAll() {
		List<Book> bookList = bookRepository.findAll();
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
		return true;
	}

	@Override
	public List<BookVo> findByUserSeq(long seq) {
		List<Book> bookList = bookRepository.findByUserSeq(seq);
		List<BookVo> results = bookList.stream()
			.map(e -> new BookVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BookVo> findByGroupSeq(long seq) {
		List<Book> bookList = bookRepository.findByGroupSeq(seq);
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
}
