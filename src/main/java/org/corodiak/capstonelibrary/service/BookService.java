package org.corodiak.capstonelibrary.service;

import java.time.LocalDate;
import java.util.List;

import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.vo.BookVo;

public interface BookService {
	List<BookVo> findAll(long start, long display);

	BookVo findBook(long seq);

	boolean addBook(String title, String author, String publisher, String isbn, String thumbnail, LocalDate publishDate,
		String description, Category category, Long userSeq, Long groupSeq);

	List<BookVo> findByUserSeq(long seq, long start, long display);

	List<BookVo> findByGroupSeq(long seq, long start, long display);

	boolean removeBook(long seq);

	boolean updateBook(long seq, String title, String author, String publisher, String isbn, String thumbnail,
		LocalDate publishDate, String description, Category category);

	boolean borrowBook(Long userSeq, Long bookSeq);

	boolean returnBook(Long userSeq, Long bookSeq);

	List<BookVo> searchBook(String keyword, String category);
}
