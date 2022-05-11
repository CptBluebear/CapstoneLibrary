package org.corodiak.capstonelibrary;

import java.io.IOException;

import org.corodiak.capstonelibrary.service.BookApiService;
import org.corodiak.capstonelibrary.type.dto.BookInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverApiTest {

	static {
		System.setProperty("jasypt.encryptor.password", "?????????");
	}

	@Autowired
	BookApiService naverBookApiService;

	@Test
	public void test() throws IOException {
		BookInfo info = naverBookApiService.searchByIsbn("9788998139766");
		Assertions.assertEquals("조영호", info.getAuthor());
		info = naverBookApiService.searchByIsbn("9788998139766XXXXXX");
		Assertions.assertEquals(null, info.getAuthor());
	}
}
