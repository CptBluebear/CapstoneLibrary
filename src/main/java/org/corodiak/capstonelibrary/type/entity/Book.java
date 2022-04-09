package org.corodiak.capstonelibrary.type.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.etc.CategoryConverter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_seq")
	private Long seq;

	@Column(
		nullable = false
	)
	private String title;

	@Column(
		nullable = false
	)
	private String author;

	@Column(
		nullable = false
	)
	private String publisher;

	@Column(
		nullable = true,
		length = 30
	)
	private String isbn;

	@Column(
		nullable = false,
		length = 30
	)
	private String code;

	@Column(
		nullable = false
	)
	private String thumbnail;

	@Column
	private LocalDate publishDate;

	@Column
	private String description;

	@Convert(converter = CategoryConverter.class)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_seq")
	private Group group;

	@Builder
	public Book(Long seq, String title, String author, String publisher, String isbn, String code,
		String thumbnail, LocalDate publishDate, String description,
		Category category, User user, Group group) {
		this.seq = seq;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.code = code;
		this.thumbnail = thumbnail;
		this.publishDate = publishDate;
		this.description = description;
		this.category = category;
		this.user = user;
		this.group = group;
	}

	@Override
	public String toString() {
		return "{" +
				"seq=" + seq +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", publisher='" + publisher + '\'' +
				", isbn='" + isbn + '\'' +
				", code='" + code + '\'' +
				", thumbnail='" + thumbnail + '\'' +
				", publishDate=" + publishDate +
				", description='" + description + '\'' +
				", category=" + category +
				'}';
	}
}
