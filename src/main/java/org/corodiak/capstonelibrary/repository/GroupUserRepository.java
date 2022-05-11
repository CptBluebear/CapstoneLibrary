package org.corodiak.capstonelibrary.repository;

import org.corodiak.capstonelibrary.type.entity.GroupUser;

public interface GroupUserRepository {

	GroupUser save(GroupUser groupUser);
	long deleteByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);

}
