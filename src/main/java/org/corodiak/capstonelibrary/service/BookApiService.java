package org.corodiak.capstonelibrary.service;

import org.corodiak.capstonelibrary.type.dto.BookInfo;
import org.corodiak.capstonelibrary.type.dto.NaverBookInfo;

public interface BookApiService {

	BookInfo searchByIsbn(String isbn);

}
