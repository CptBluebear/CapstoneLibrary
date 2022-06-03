package org.corodiak.capstonelibrary;

import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.repository.GroupRepositoryImpl;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationSearchTest {

	static {
		System.setProperty("jasypt.encryptor.password", "?????????");
	}

	@Autowired
	GroupRepositoryImpl groupRepository;

	@Test
	void test() {
		List<Long> seq = new ArrayList<>();
		seq.add(1L);
		seq.add(3L);
		List<Group> results = groupRepository.searchInList("TRA", seq);
		for (Group g : results) {
			System.out.println(g.getSeq());
		}
	}
}
