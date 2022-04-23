package org.corodiak.capstonelibrary.type.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.etc.BookLogStatusConverter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookLog extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booklog_seq")
	private Long seq;

	@Convert(converter = BookLogStatusConverter.class)
	private BookLogStatus bookLogStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_seq")
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_seq")
	private Group group;

	@Builder
	public BookLog(Long seq, BookLogStatus bookLogStatus, User user, Book book, Group group) {
		this.seq = seq;
		this.bookLogStatus = bookLogStatus;
		this.user = user;
		this.book = book;
		this.group = group;
	}
}
