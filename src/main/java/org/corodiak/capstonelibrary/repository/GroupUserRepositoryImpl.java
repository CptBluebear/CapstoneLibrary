package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.GroupUser;
import org.corodiak.capstonelibrary.type.entity.QGroupUser;
import org.corodiak.capstonelibrary.type.entity.QUser;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupUserRepositoryImpl implements GroupUserRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;

	private QGroupUser qGroupUser = QGroupUser.groupUser;
	private QUser qUser = QUser.user;

	@Override
	@Transactional
	public GroupUser save(GroupUser groupUser) {
		entityManager.merge(groupUser);
		return groupUser;
	}

	@Override
	public long deleteByUserSeqAndGroupSeq(Long userSeq, Long groupSeq) {
		return queryFactory.delete(qGroupUser)
			.where(qGroupUser.user.seq.eq(userSeq).and(qGroupUser.group.seq.eq(groupSeq)))
			.execute();
	}

	@Override
	public Optional<GroupUser> findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq) {
		GroupUser groupUser = queryFactory.selectFrom(qGroupUser)
			.where(qGroupUser.group.seq.eq(groupSeq)
				.and(qGroupUser.user.seq.eq(userSeq)))
			.fetchOne();
		return Optional.ofNullable(groupUser);
	}

	@Override
	public List<GroupUser> findUserByGroupSeq(Long groupSeq) {
		List<GroupUser> results = queryFactory.selectFrom(qGroupUser)
			.where(qGroupUser.group.seq.eq(groupSeq))
			.innerJoin(qGroupUser.user, qUser)
			.fetchJoin()
			.fetch();
		return results;
	}

	@Override
	public List<GroupUser> findByUserSeq(Long userSeq) {
		List<GroupUser> results = queryFactory.selectFrom(qGroupUser)
			.where(qGroupUser.user.seq.eq(userSeq))
			.fetch();
		return results;
	}
}
