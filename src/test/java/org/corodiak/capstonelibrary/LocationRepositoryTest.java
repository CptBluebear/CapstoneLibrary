package org.corodiak.capstonelibrary;

import java.util.Optional;

import org.corodiak.capstonelibrary.repository.LibraryPointRepository;
import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class LocationRepositoryTest {

	static {
		System.setProperty("jasypt.encryptor.password", "??????????");
	}

	@Autowired
	LibraryPointRepository repository;

	@Test
	@Rollback
	public void test() {
		repository.save(99999, "TESTLOC", 127, 36);
		Optional<LocationPoint> locationPoint = repository.findByLocationSeq(99999);
		Assertions.assertEquals(locationPoint.get().getName(), "TESTLOC");
		repository.delete(99999);
		locationPoint = repository.findByLocationSeq(99999);
		Assertions.assertFalse(locationPoint.isPresent());
	}

}
