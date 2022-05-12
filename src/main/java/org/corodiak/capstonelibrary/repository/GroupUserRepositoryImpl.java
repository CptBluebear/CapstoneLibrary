package org.corodiak.capstonelibrary.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.GroupUser;
import org.corodiak.capstonelibrary.type.entity.QGroupUser;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupUserRepositoryImpl implements GroupUserRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;

	private QGroupUser qGroupUser = QGroupUser.groupUser;

	@Override
	@Transactional
	public GroupUser save(GroupUser groupUser) {
		entityManager.persist(groupUser);
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
}
