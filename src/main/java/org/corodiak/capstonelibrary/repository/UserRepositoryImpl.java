package org.corodiak.capstonelibrary.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.QBook;
import org.corodiak.capstonelibrary.type.entity.QUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final EntityManager entityManager;

	private final JPAQueryFactory jpaQueryFactory;
	private QUser qUser = QUser.user;
	private QBook qBook = QBook.book;

	@Override
	public Optional<User> findBySeq(Long seq) {
		User user = jpaQueryFactory.selectFrom(qUser)
			.where(qUser.seq.eq(seq))
			.fetchOne();
		Optional<User> result = Optional.ofNullable(user);
		return result;
	}

	@Override
	@Transactional
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	@Transactional
	public Optional<User> findBySeqWithBookList(Long seq) {
		User user = jpaQueryFactory.selectFrom(qUser)
			.where(qUser.seq.eq(seq))
			.join(qUser.bookList)
			.fetchJoin()
			.fetchOne();
		Optional<User> result = Optional.ofNullable(user);
		return result;
	}
}
