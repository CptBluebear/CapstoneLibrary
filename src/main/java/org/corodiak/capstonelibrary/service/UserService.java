package org.corodiak.capstonelibrary.service;

import java.util.List;

public interface UserService {
	boolean updateNicknameByUserSeq(Long userSeq, String nickname);
	String findNicknameByUserSeq(Long userSeq);
	List<String> findEmailByUserSeq(Long userSeq);
}
