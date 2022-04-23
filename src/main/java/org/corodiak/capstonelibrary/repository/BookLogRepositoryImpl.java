package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.BookLog;
import org.corodiak.capstonelibrary.type.entity.QBookLog;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookLogRepositoryImpl implements BookLogRepository {
	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;
	private QBookLog qBookLog = QBookLog.bookLog;

	@Override
	@Transactional
	public BookLog save(BookLog bookLog) {
		entityManager.persist(bookLog);
		return bookLog;
	}

	@Override
	@Transactional
	public long deleteBySeq(Long seq) {
		return queryFactory.delete(qBookLog)
			.where(qBookLog.seq.eq(seq))
			.execute();
	}

	@Override
	public List<BookLog> findAll() {
		List<BookLog> results = queryFactory.selectFrom(qBookLog)
			.fetch();
		return results;
	}

	@Override
	public Optional<BookLog> findBySeq(Long seq) {
		BookLog result = queryFactory.selectFrom(qBookLog)
			.where(qBookLog.seq.eq(seq))
			.fetchOne();
		return Optional.ofNullable(result);
	}

	@Override
	public List<BookLog> findByUserSeq(Long userSeq) {
		List<BookLog> results = queryFactory.selectFrom(qBookLog)
			.where(qBookLog.user.seq.eq(userSeq))
			.fetch();
		return results;
	}

	@Override
	public List<BookLog> findByBookSeq(Long bookSeq) {
		List<BookLog> results = queryFactory.selectFrom(qBookLog)
			.where(qBookLog.book.seq.eq(bookSeq))
			.fetch();
		return results;
	}

	@Override
	public List<BookLog> findByGroupSeq(Long groupSeq) {
		List<BookLog> results = queryFactory.selectFrom(qBookLog)
			.where(qBookLog.group.seq.eq(groupSeq))
			.fetch();
		return results;
	}

	@Override
	public List<BookLog> findByGroupAndUserSeq(Long groupSeq, Long userSeq) {
		List<BookLog> results = queryFactory.selectFrom(qBookLog)
			.where(qBookLog.group.seq.eq(groupSeq).and(qBookLog.user.seq.eq(userSeq)))
			.fetch();
		return results;
	}
}
