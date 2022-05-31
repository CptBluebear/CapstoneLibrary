package org.corodiak.capstonelibrary.repository;

import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.User;

public interface UserRepository {
	Optional<User> findBySeq(Long seq);

	User save(User user);

	Optional<User> findBySeqWithBookList(Long seq);

	Long updateNicknameBySeq(Long seq, String nickname);
}
