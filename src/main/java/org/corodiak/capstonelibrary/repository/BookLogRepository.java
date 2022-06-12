package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.BookLog;

public interface BookLogRepository {
	@Transactional
	BookLog save(BookLog bookLog);

	@Transactional
	long deleteBySeq(Long seq);

	List<BookLog> findAll();

	Optional<BookLog> findBySeq(Long seq);

	List<BookLog> findByUserSeq(Long userSeq);

	List<BookLog> findByBookSeq(Long bookSeq);

	List<BookLog> findByGroupSeq(Long groupSeq);

	List<BookLog> findByGroupAndUserSeq(Long groupSeq, Long userSeq);

	List<BookLog> findMyBorrow(Long userSeq, Long start, Long display);

	@Transactional
	Long returnBookLog(Long userSeq, Long bookSeq);
}
