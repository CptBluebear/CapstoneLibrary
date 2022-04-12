package org.corodiak.capstonelibrary.type.vo;

import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;

import lombok.Getter;

@Getter
public class GroupVo {

	private Long seq;
	private String name;
	private boolean isOpen;
	private String thumbnail;

	public GroupVo(Group entity) {
		this.seq = entity.getSeq();
		this.name = entity.getName();
		this.isOpen = entity.isOpen();
		this.thumbnail = entity.getThumbnail();
	}

	public static class GroupVoWithAdmin extends GroupVo {
		private UserVo admin;
		public GroupVoWithAdmin(Group entity) {
			super(entity);
			admin = new UserVo(entity.getUser());
		}
	}

	public static class GroupVoWithBookList extends GroupVo {
		private List<BookVo> bookList;
		public GroupVoWithBookList(Group entity) {
			super(entity);
			List<Book> data = entity.getBookList();
			bookList = new ArrayList<>();
			for(Book b:data) {
				BookVo book = new BookVo(b);
				bookList.add(book);
			}
		}
	}
}
