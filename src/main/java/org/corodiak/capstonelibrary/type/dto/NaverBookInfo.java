package org.corodiak.capstonelibrary.type.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.corodiak.capstonelibrary.Exception.BookApiResultNullException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class NaverBookInfo extends BookInfo {

	public NaverBookInfo(String rawData) {
		JsonElement jsonElement = JsonParser.parseString(rawData);
		if(jsonElement.getAsJsonObject().get("items").getAsJsonArray().size() < 1) {
			throw new BookApiResultNullException("Not Exist Search Result!!");
		}
		JsonObject bookData = jsonElement.getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject();
		this.title = bookData.get("title").getAsString();
		this.author = bookData.get("author").getAsString();
		this.publisher = bookData.get("publisher").getAsString();
		this.isbn = bookData.get("isbn").getAsString();
		this.description = bookData.get("description").getAsString();
		this.thumbnail = bookData.get("image").getAsString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		this.publishDate = LocalDate.parse(bookData.get("pubdate").getAsString(), formatter);
	}

}
