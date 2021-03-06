package org.corodiak.capstonelibrary.type.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.capstonelibrary.type.entity.id.GroupUserId;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(GroupUserId.class)
public class GroupUser extends BaseTimeEntity {

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_seq")
	private Group group;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_seq")
	private User user;

	@Builder
	public GroupUser(Group group, User user) {
		this.group = group;
		this.user = user;
	}
}
