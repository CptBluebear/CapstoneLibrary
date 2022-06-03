package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.GroupUser;

public interface GroupUserRepository {

	GroupUser save(GroupUser groupUser);

	long deleteByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);

	Optional<GroupUser> findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);

	List<GroupUser> findUserByGroupSeq(Long groupSeq);
}
