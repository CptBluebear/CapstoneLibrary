package org.corodiak.capstonelibrary;

import java.io.IOException;

import org.corodiak.capstonelibrary.service.NaverBookApiService;
import org.corodiak.capstonelibrary.type.dto.NaverBookInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverApiTest {

	@Autowired
	NaverBookApiService naverBookApiService;

	@Test
	public void test() throws IOException {
		NaverBookInfo info = naverBookApiService.searchByIsbn("9788998139766");
		Assertions.assertEquals("조영호", info.getAuthor());
		info = naverBookApiService.searchByIsbn("9788998139766XXXXXX");
		Assertions.assertEquals(null, info.getAuthor());
	}

}
