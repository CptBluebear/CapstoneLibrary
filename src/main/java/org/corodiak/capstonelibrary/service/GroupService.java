package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.vo.GroupVo;

public interface GroupService {
	public void save(String name, String thumbnail, String authenticationCode, boolean isOpen);

	public List<GroupVo> findAll();

	public List<GroupVo> getPublicGroupList();

	public GroupVo getById(Long seq);

	public List<GroupVo> getByAdminUserId(Long seq);

	public void updateById(Long seq, Group group);

	public void deleteById(Long seq);
}
