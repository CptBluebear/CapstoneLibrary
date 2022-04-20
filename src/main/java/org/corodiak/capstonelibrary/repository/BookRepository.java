package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Book;

public interface BookRepository {

	Book save(Book book);
	long deleteBySeq(Long seq);
	Optional<Book> findBySeq(Long seq);
	List<Book> findAll();
	List<Book> findByUserSeq(Long userSeq);

	List<Book> findByGroupSeq(Long groupSer);
}
