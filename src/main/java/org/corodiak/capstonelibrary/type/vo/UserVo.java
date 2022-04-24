package org.corodiak.capstonelibrary.type.vo;

import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.GroupUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.etc.Role;

import net.minidev.json.annotate.JsonIgnore;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserVo {

	private Long seq;
	private String nickname;
	@JsonIgnore
	private Role role;

	public UserVo(User entity) {
		this.seq = entity.getSeq();
		this.nickname = entity.getNickname();
		this.role = entity.getRole();
	}

	@Getter
	@ToString(callSuper = true)
	public static class UserVoWithAdminList extends UserVo {
		private List<GroupVo> adminList;

		public UserVoWithAdminList(User entity) {
			super(entity);
			List<Group> data = entity.getAdminList();
			adminList = new ArrayList<>();
			for (Group g : data) {
				GroupVo group = new GroupVo(g);
				adminList.add(group);
			}
		}
	}

	@Getter
	@ToString(callSuper = true)
	public static class UserVoWithBookList extends UserVo {
		private List<BookVo> bookList;

		public UserVoWithBookList(User entity) {
			super(entity);
			List<Book> data = entity.getBookList();
			bookList = new ArrayList<>();
			for (Book b : data) {
				BookVo book = new BookVo(b);
				bookList.add(book);
			}
		}
	}

	@Getter
	@ToString(callSuper = true)
	public static class UserVoWithGroupList extends UserVo {
		private List<GroupVo> groupList;

		public UserVoWithGroupList(User entity) {
			super(entity);
			List<GroupUser> data = entity.getGroups();
			groupList = new ArrayList<>();
			for (GroupUser group : data) {
				GroupVo groupVo = new GroupVo(group.getGroup());
				groupList.add(groupVo);
			}
		}
	}
}
