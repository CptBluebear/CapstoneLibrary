package org.corodiak.capstonelibrary;

import java.time.LocalDate;

import org.corodiak.capstonelibrary.service.BookService;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTest {

	@Autowired
	BookService bookService;

	static {
		System.setProperty("jasypt.encryptor.password", "??????????");
	}

	@Test
	public void test() {
		bookService.updateBook(13, "TRAGE", "TTTT", "TTTT", "1234567890", "testlink", LocalDate.now(), "TTTT",
			Category.SOS);
	}

}
