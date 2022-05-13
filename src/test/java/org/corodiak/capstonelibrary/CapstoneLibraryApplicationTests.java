package org.corodiak.capstonelibrary;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.bytebuddy.utility.RandomString;

class CapstoneLibraryApplicationTests {

	@Test
	void contextLoads() {
		Random random = new Random();
		RandomString rs = new RandomString(10, random);
		System.out.println(rs.nextString());
	}

}
