package org.corodiak.capstonelibrary;

import java.util.Optional;

import org.corodiak.capstonelibrary.repository.UserRepository;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.vo.UserVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void test1() {
		Optional<User> result = userRepository.findBySeq(Long.valueOf(1));
		UserVo user = null;
		if(result.isPresent()) {
			user = new UserVo(result.get());
		} else {
			Assertions.fail("유저 정보가 로드되지 않음");
		}
		Assertions.assertEquals("나락", user.getNickname());
	}

	@Test
	public void test2() {
		Optional<User> result = userRepository.findBySeqWithBookList(Long.valueOf(2));
		UserVo user = null;
		if(result.isPresent()) {
			user = new UserVo.UserVoWithBookList(result.get());
		} else {
			Assertions.fail("유저 정보가 로드되지 않음");
		}
		System.out.println(user.toString());
	}

}
