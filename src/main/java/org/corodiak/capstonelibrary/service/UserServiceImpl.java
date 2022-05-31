package org.corodiak.capstonelibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.OAuthUserRepository;
import org.corodiak.capstonelibrary.repository.UserRepository;
import org.corodiak.capstonelibrary.type.entity.OAuthUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final OAuthUserRepository oAuthUserRepository;

	@Override
	public boolean updateNicknameByUserSeq(Long userSeq, String nickname) {
		userRepository.updateNicknameBySeq(userSeq, nickname);
		return true;
	}

	@Override
	public String findNicknameByUserSeq(Long userSeq) {
		Optional<User> user = userRepository.findBySeq(userSeq);
		if(!user.isPresent()) {
			throw new SearchResultNotExistException();
		}
		return user.get().getNickname();
	}

	@Override
	public List<String> findEmailByUserSeq(Long userSeq) {
		List<OAuthUser> results = oAuthUserRepository.findByUserSeq(userSeq);
		List<String> emails = new ArrayList<>();
		for(OAuthUser oAuthUser:results) {
			emails.add(oAuthUser.getEmail());
		}
		return emails;
	}
}
