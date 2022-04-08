package org.corodiak.capstonelibrary.type.etc;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {
	@Override
	public String convertToDatabaseColumn(Category attribute) {
		return attribute.getCode();
	}

	@Override
	public Category convertToEntityAttribute(String dbData) {
		return Category.ofCode(dbData);
	}
}
