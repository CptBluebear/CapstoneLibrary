package org.corodiak.capstonelibrary.type.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.ToString;

@Getter
public class GroupVo {

	private Long seq;
	private String name;
	private double longtitude;
	private double latitude;
	private boolean isOpen;
	private String thumbnail;
	private String description;
	@JsonIgnore
	private String authenticationCode;
	private LocalDateTime createdDate;

	public GroupVo(Group entity) {
		this.seq = entity.getSeq();
		this.name = entity.getName();
		this.longtitude = entity.getLongtitude();
		this.latitude = entity.getLatitude();
		this.isOpen = entity.isOpen();
		this.thumbnail = entity.getThumbnail();
		this.description = entity.getDescription();
		this.authenticationCode = entity.getAuthenticationCode();
		this.createdDate = entity.getCreatedDate();
	}

	@Getter
	@ToString(callSuper = true)
	public static class GroupVoWithAdmin extends GroupVo {
		private UserVo admin;

		public GroupVoWithAdmin(Group entity) {
			super(entity);
			admin = new UserVo(entity.getUser());
		}
	}

	@Getter
	@ToString(callSuper = true)
	public static class GroupVoWithBookList extends GroupVo {
		private List<BookVo> bookList;

		public GroupVoWithBookList(Group entity) {
			super(entity);
			List<Book> data = entity.getBookList();
			bookList = new ArrayList<>();
			for (Book b : data) {
				BookVo book = new BookVo(b);
				bookList.add(book);
			}
		}
	}
}
