package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Group;

public interface GroupService {
	public void save(Group group);

	public List<Group> findAll();

	public Group getById(Long seq);

	public Group getByUserId(Long seq);

	public void updateById(Long seq, Group group);

	public void deleteById(Long seq);
}
