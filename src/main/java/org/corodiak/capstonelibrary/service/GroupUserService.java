package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.vo.UserVo;

public interface GroupUserService {
	boolean addGroupUser(Long groupSeq, Long userSeq);

	boolean removeGroupUser(Long userSeq, Long groupSeq);

	boolean findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);

	List<UserVo> findUserByGroupSeq(Long groupSeq);
}
