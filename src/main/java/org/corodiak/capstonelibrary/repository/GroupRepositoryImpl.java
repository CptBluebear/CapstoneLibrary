package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.QBook;
import org.corodiak.capstonelibrary.type.entity.QGroup;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;

	QGroup qGroup = QGroup.group;
	QBook qBook = QBook.book;

	@Override
	@Transactional
	public Group save(Group group) {
		entityManager.persist(group);
		return group;
	}

	@Override
	@Transactional
	public long deleteBySeq(Long seq) {
		return queryFactory.delete(qGroup)
			.where(qGroup.seq.eq(seq)).execute();
	}

	@Override
	public Optional<Group> findBySeq(Long seq) {
		Group group = queryFactory.selectFrom(qGroup)
			.where(qGroup.seq.eq(seq))
			.fetchOne();
		return Optional.ofNullable(group);
	}

	@Override
	public List<Group> findByUserSeq(Long userSeq) {
		List<Group> results = queryFactory.selectFrom(qGroup)
			.where(qGroup.user.seq.eq(userSeq))
			.fetch();
		return results;
	}

	@Override
	public List<Group> findGroupIsOpen() {
		List<Group> results = queryFactory.selectFrom(qGroup)
			.where(qGroup.isOpen.isTrue())
			.fetch();
		return results;
	}

	@Override
	public Optional<Group> findByAuthenticationCode(String authenticationCode) {
		Group group = queryFactory.selectFrom(qGroup)
			.where(qGroup.authenticationCode.eq(authenticationCode))
			.fetchOne();
		return Optional.ofNullable(group);
	}

	@Override
	@Transactional
	public Long authorizeAdmin(Long groupSeq, Long userSeq) {
		return queryFactory.update(qGroup)
			.set(qGroup.user.seq, userSeq)
			.where(qGroup.seq.eq(groupSeq))
			.execute();
	}

	@Override
	public List<Group> search(String keyword) {
		List<Group> results = queryFactory.selectFrom(qGroup)
			.where(qGroup.name.contains(keyword).or(qGroup.description.contains(keyword)))
			.fetch();
		return results;
	}

	@Override
	public List<Group> searchInList(String keyword, List<Long> seq) {
		return queryFactory.selectFrom(qGroup)
			.leftJoin(qBook)
			.on(qGroup.eq(qBook.group))
			.where(qBook.title.contains(keyword).and(qGroup.seq.in(seq)))
			.fetch();
	}
}
