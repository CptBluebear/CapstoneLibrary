package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Group;

public interface GroupService {
	public void save(Group group);

	public List<Group> findAll();

	public List<Group> getPublicGroupList();

	public Group getById(Long seq);

	public List<Group> getByAdminUserId(Long seq);

	public void updateById(Long seq, Group group);

	public void deleteById(Long seq);
}
