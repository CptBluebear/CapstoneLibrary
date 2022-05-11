package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.Group;
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
		return null;
	}
}
