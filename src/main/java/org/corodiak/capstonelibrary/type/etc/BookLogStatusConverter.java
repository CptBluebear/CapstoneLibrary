package org.corodiak.capstonelibrary.type.etc;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BookLogStatusConverter implements AttributeConverter<BookLogStatus, String> {
	@Override
	public String convertToDatabaseColumn(BookLogStatus attribute) {
		return attribute.getCode();
	}

	@Override
	public BookLogStatus convertToEntityAttribute(String dbData) {
		return BookLogStatus.ofCode(dbData);
	}
}
