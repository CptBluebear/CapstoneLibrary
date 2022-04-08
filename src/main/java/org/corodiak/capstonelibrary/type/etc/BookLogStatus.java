package org.corodiak.capstonelibrary.type.etc;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookLogStatus {
	BORROW("1", "Book Borrow"),
	RETURN("2", "Book Return");

	private String code;
	private String description;

	public static BookLogStatus ofCode(String code) {
		return Arrays.stream(BookLogStatus.values())
			.filter(value -> value.getCode().equals(code))
			.findAny()
			.orElse(BookLogStatus.RETURN);
	}
}
