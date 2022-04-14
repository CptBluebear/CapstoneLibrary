package org.corodiak.capstonelibrary.service;

import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public void save(String title, String author, String publisher, String isbn, String code, String thumbnail,
		LocalDate publishDate, String description, String category) {

		Book book = Book.builder()
			.title(title)
			.author(author)
			.publisher(publisher)
			.isbn(isbn)
			.code(code)
			.thumbnail(thumbnail)
			.publishDate(publishDate)
			.description(description)
			.category(Category.ofCode(category))
			//.user(user.getUsername())
			//.group(group)
			.build();

		BookVo bookVo = new BookVo(book);

		bookRepository.save(bookVo);
	}

	@Override
	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(b -> bookList.add(b));
		return bookList;
	}

	@Override
	public Book getById(Long seq) {
		Book book = bookRepository.findById(seq).get();
		return book;
	}

	@Override
	public List<Book> getByUserId(Long seq) {
		List<Book> bookList = bookRepository.findByUserSeq(seq);
		return bookList;
	}

	@Override
	public List<Book> getByGroupId(Long seq) {
		return null;
	}

	@Override
	public void updateById(Long seq, Book book) {
		Optional<Book> e = bookRepository.findById(seq);

		if (e.isPresent()) {
			BookVo bookVo = new BookVo(e.get());
		}
	}

	@Override
	public void deleteById(Long seq) {
		bookRepository.deleteById(seq);
	}

	@Override
	public List<Book> getByKeyword(String keyword) {
		return null;
	}
}
