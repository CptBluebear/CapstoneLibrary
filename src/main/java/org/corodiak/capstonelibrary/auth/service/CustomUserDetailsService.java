package org.corodiak.capstonelibrary.auth.service;

import java.util.Optional;

import org.corodiak.capstonelibrary.repository.UserRepository;
import org.corodiak.capstonelibrary.type.dto.UserPrincipal;
import org.corodiak.capstonelibrary.type.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("customuserdetailservice active");
		Optional<User> user = userRepository.findBySeq(Long.parseLong(username));
		log.info("data : {}", user);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return UserPrincipal.create(user.get());
	}
}
