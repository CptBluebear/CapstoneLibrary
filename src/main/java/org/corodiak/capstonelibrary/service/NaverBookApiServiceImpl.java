package org.corodiak.capstonelibrary.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.corodiak.capstonelibrary.Exception.BookApiResultNullException;
import org.corodiak.capstonelibrary.type.dto.NaverBookInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverBookApiServiceImpl implements BookApiService {

	@Value("${api.external.client.id}")
	private String clientId;

	@Value("${api.external.client.secret}")
	private String clientSecret;

	private final String API_LINK = "https://openapi.naver.com/v1/search/book.json?query=";

	@Override
	public NaverBookInfo searchByIsbn(String isbn) {

		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(API_LINK + isbn).openConnection();
			connection.setRequestProperty("X-Naver-Client-Id", clientId);
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				throw new BookApiResultNullException("Api Request Error!!");
			}

			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return new NaverBookInfo(stringBuilder.toString());
		} catch (IOException | BookApiResultNullException e1) {
			return new NaverBookInfo();
		}
	}
}
