package org.corodiak.capstonelibrary.type.vo;

import java.time.LocalDate;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.etc.Category;

import lombok.Getter;

@Getter
public class BookVo {

	private Long seq;
	private String title;
	private String author;
	private String publisher;
	private String isbn;
	private String code;
	private String thumbnail;
	private LocalDate publishDate;
	private String description;
	private Category category;

	public BookVo(Book entity) {
		this.seq = entity.getSeq();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.publisher = entity.getPublisher();
		this.isbn = entity.getIsbn();
		this.code = entity.getCode();
		this.thumbnail = entity.getThumbnail();
		this.publishDate = entity.getPublishDate();
		this.description = entity.getDescription();
		this.category = entity.getCategory();
	}

	@Getter
	public static class BookVoWithUserAndGroup extends BookVo {

		private UserVo user;
		private GroupVo group;

		public BookVoWithUserAndGroup(Book entity) {
			super(entity);
			user = new UserVo(entity.getUser());
			group = new GroupVo(entity.getGroup());
		}
	}
}
