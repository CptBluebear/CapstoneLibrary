package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.QBook;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;

	private QBook qBook = QBook.book;

	@Override
	@Transactional
	public Book save(Book book) {
		entityManager.persist(book);
		return book;
	}

	@Override
	@Transactional
	public long deleteBySeq(Long seq) {
		return queryFactory.delete(qBook)
			.where(qBook.seq.eq(seq))
			.execute();
	}

	@Override
	public Optional<Book> findBySeq(Long seq) {
		Book result = queryFactory.selectFrom(qBook)
			.where(qBook.seq.eq(seq))
			.fetchOne();
		return Optional.ofNullable(result);
	}

	@Override
	public List<Book> findAll(long start, long display) {
		List<Book> results = queryFactory.selectFrom(qBook)
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Book> findByUserSeq(Long userSeq, long start, long display) {
		List<Book> results = queryFactory.selectFrom(qBook)
			.where(qBook.user.seq.eq(userSeq))
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Book> findByGroupSeq(Long groupSeq, long start, long display) {
		List<Book> results = queryFactory.selectFrom(qBook)
			.where(qBook.group.seq.eq(groupSeq))
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	@Transactional
	public Long borrowBook(Long seq) {
		return queryFactory.update(qBook)
			.set(qBook.isRental, true)
			.where(qBook.seq.eq(seq))
			.execute();
	}

	@Override
	@Transactional
	public Long returnBook(Long seq) {
		return queryFactory.update(qBook)
			.set(qBook.isRental, false)
			.where(qBook.seq.eq(seq))
			.execute();
	}
}
