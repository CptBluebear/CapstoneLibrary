package org.corodiak.capstonelibrary.service;

public interface GroupUserService {
	boolean addGroupUser(Long groupSeq, Long userSeq);

	boolean removeGroupUser(Long userSeq, Long groupSeq);

	boolean findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq);
}
