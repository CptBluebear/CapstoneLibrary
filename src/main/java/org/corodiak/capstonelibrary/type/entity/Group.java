package org.corodiak.capstonelibrary.type.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "groups")
public class Group extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_seq")
	private Long seq;

	@Column(
		nullable = false,
		length = 60
	)
	private String name;

	@Column
	double longtitude;

	@Column
	double latitude;

	@Column(
		name = "open_yn",
		nullable = false
	)
	private boolean isOpen;

	@Column(
		name = "authentication_code",
		nullable = false,
		length = 10
	)
	private String authenticationCode;

	@Column(
		nullable = false
	)
	private String thumbnail;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_seq")
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private List<Book> bookList = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<GroupUser> users = new ArrayList<>();

	@Builder
	public Group(Long seq, String name, boolean isOpen, String authenticationCode, String thumbnail,
		User user, double longtitude, double latitude) {
		this.seq = seq;
		this.name = name;
		this.isOpen = isOpen;
		this.authenticationCode = authenticationCode;
		this.thumbnail = thumbnail;
		this.user = user;
		this.longtitude = longtitude;
		this.latitude = latitude;
	}
}
