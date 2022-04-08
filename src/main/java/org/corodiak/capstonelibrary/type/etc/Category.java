package org.corodiak.capstonelibrary.type.etc;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
	GEN("0", "Computer science, information & general works"),
	PNP("1", "Philosophy & psychology"),
	REL("2", "Religion"),
	SOS("3", "Social sciences"),
	LAN("4", "Language"),
	SCI("5", "Science"),
	TEC("6", "Technology"),
	ANR("7", "Arts & recreation"),
	LIT("8", "Literature"),
	HNG("9", "History & geography");

	private String code;
	private String description;

	public static Category ofCode(String code) {
		return Arrays.stream(Category.values())
			.filter(value -> value.getCode().equals(code))
			.findAny()
			.orElse(Category.GEN);
	}
}
