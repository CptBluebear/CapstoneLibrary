package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.QGroup;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

	private final EntityManager entityManager;
	private final JPAQueryFactory jpaQueryFactory;

	private QGroup qGroup = QGroup.group;

	@Override
	@Transactional
	public void save(GroupVo group) {
		Group groupEntity = Group.builder()
			.thumbnail(group.getThumbnail())
			.isOpen(group.isOpen())
			.name(group.getName())
			.authenticationCode(group.getAuthenticationCode())
			.build();

		entityManager.merge(groupEntity);
	}

	@Override
	public List<Group> findAll() {
		List<Group> groupList = jpaQueryFactory.selectFrom(qGroup)
			.fetch();
		return groupList;
	}

	@Override
	public Optional<Group> findById(Long seq) {
		Group group = jpaQueryFactory.selectFrom(qGroup)
			.where(qGroup.seq.eq(seq))
			.fetchOne();
		return Optional.ofNullable(group);
	}

	@Override
	public List<Group> findByUserSeq(Long seq) {
		List<Group> groupList = jpaQueryFactory.selectFrom(qGroup)
			.where(qGroup.user.seq.eq(seq))
			.orderBy(qGroup.seq.asc())
			.fetch();
		return groupList;
	}

	@Override
	public List<Group> findByIsOpenFalse() {
		List<Group> groupList = jpaQueryFactory.selectFrom(qGroup)
			.where(qGroup.isOpen.isFalse())
			.orderBy(qGroup.seq.asc())
			.fetch();
		return groupList;
	}

	@Override
	@Transactional
	public void deleteById(Long seq) {
		jpaQueryFactory.delete(qGroup).where(qGroup.seq.eq(seq)).execute();
	}
}
