package org.corodiak.capstonelibrary.service;

import java.io.IOException;

import org.corodiak.capstonelibrary.type.dto.NaverBookInfo;

public interface NaverBookApiService {

	NaverBookInfo searchByIsbn(String isbn);

}
