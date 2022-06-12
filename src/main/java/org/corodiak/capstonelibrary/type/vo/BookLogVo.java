package org.corodiak.capstonelibrary.type.vo;

import java.time.LocalDateTime;

import org.corodiak.capstonelibrary.type.entity.BookLog;
import org.corodiak.capstonelibrary.type.etc.BookLogStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookLogVo {

	private Long seq;
	private BookLogStatus bookLogStatus;
	private LocalDateTime createdTime;
	private LocalDateTime lastModifiedTime;

	public BookLogVo(BookLog entity) {
		this.seq = entity.getSeq();
		this.bookLogStatus = entity.getBookLogStatus();
		this.createdTime = entity.getCreatedDate();
		this.lastModifiedTime = entity.getLastModifiedDate();
	}

	@Getter
	@ToString(callSuper = true)
	public static class BookLogVoWithUserAndBookAndGroup extends BookLogVo {

		private UserVo user;
		private BookVo book;
		private GroupVo group;

		public BookLogVoWithUserAndBookAndGroup(BookLog entity) {
			super(entity);
			user = new UserVo(entity.getUser());
			book = new BookVo(entity.getBook());
			group = new GroupVo(entity.getGroup());
		}
	}
}
