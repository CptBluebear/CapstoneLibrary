package org.corodiak.capstonelibrary.service;

import org.corodiak.capstonelibrary.type.dto.BookInfo;

public interface BookApiService {

	BookInfo searchByIsbn(String isbn);

}
