package org.corodiak.capstonelibrary.type.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IsbnData extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Seq;

	@Column
	private String title;

	@Column
	private String author;

	@Column
	private String publisher;

	@Column
	private String isbn;

	@Column
	private LocalDate publishDate;

	@Column
	private String description;

	@Builder
	public IsbnData(Long seq, String title, String author, String publisher, String isbn, LocalDate publishDate,
		String description) {
		Seq = seq;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.publishDate = publishDate;
		this.description = description;
	}
}
