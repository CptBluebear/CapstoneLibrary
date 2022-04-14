package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.QBook;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory jpaQueryFactory;

	private QBook qBook = QBook.book;

	@Override
	public void save(BookVo book) {
		entityManager.merge(book);
	}

	@Override
	@Transactional
	public List<Book> findByUserSeq(Long seq) {
		List<Book> bookList = jpaQueryFactory.selectFrom(qBook)
			.where(qBook.user.seq.eq(seq))
			.orderBy(qBook.seq.asc())
			.fetch();
		return bookList;
	}

	@Override
	public List<Book> findAll() {
		System.out.println(jpaQueryFactory);
		List<Book> bookList = jpaQueryFactory.selectFrom(qBook)
			.fetch();
		return bookList;
	}

	@Override
	public Optional<Book> findById(Long seq) {
		Book book = jpaQueryFactory.selectFrom(qBook)
			.where(qBook.seq.eq(seq))
			.fetchOne();
		return Optional.ofNullable(book);
	}

	@Override
	public long deleteById(Long seq) {
		return jpaQueryFactory.delete(qBook).where(qBook.seq.eq(seq)).execute();
	}
}
