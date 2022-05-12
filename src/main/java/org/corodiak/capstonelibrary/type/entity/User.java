package org.corodiak.capstonelibrary.type.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.corodiak.capstonelibrary.type.etc.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long seq;

	@Column
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Group> adminList = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Book> bookList = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<GroupUser> groups = new ArrayList<>();

	@Builder
	public User(Long seq, String nickname, Role role) {
		this.seq = seq;
		this.nickname = nickname;
		this.role = role;
	}
}
