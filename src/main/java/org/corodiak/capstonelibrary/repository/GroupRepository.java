package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.vo.GroupVo;

public interface GroupRepository {
	void save(GroupVo group);

	List<Group> findAll();

	Optional<Group> findById(Long seq);

	public List<Group> findByUserSeq(Long seq);

	public List<Group> findByIsOpenFalse();

	void deleteById(Long seq);
}
