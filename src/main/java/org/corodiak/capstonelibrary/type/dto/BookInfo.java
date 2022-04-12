package org.corodiak.capstonelibrary.type.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public abstract class BookInfo {
	protected String title;
	protected String author;
	protected String publisher;
	protected LocalDate publishDate;
	protected String isbn;
	protected String description;
	protected String thumbnail;
}
