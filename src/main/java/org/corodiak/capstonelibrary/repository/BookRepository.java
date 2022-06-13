package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Book;

public interface BookRepository {

	Book save(Book book);

	long deleteBySeq(Long seq);

	Optional<Book> findBySeq(Long seq);

	List<Book> findAll(long start, long display);

	List<Book> findByUserSeq(Long userSeq, long start, long display);

	List<Book> findByGroupSeq(Long groupSer, long start, long display);

	Long borrowBook(Long seq);

	Long returnBook(Long seq);

	List<Book> search(String keyword, String category);

	Long deleteByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);
}
