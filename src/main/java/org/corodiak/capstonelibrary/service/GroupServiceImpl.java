package org.corodiak.capstonelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.repository.GroupRepository;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupRepository groupRepository;

	@Override
	public void save(Group group) {
		groupRepository.save(group);
	}

	@Override
	public List<GroupVo> findAll() {
		List<GroupVo> groupList = new ArrayList<>();
		groupRepository.findAll().forEach(g -> groupList.add(new GroupVo.GroupVoWithAdmin(g)));
		return groupList;
	}

	@Override
	public List<GroupVo> getPublicGroupList() {
		List<GroupVo> groupList = new ArrayList<>();
		groupRepository.findByIsOpenFalse().forEach(g -> groupList.add(new GroupVo.GroupVoWithAdmin(g)));
		return groupList;
	}

	@Override
	public GroupVo getById(Long seq) {
		GroupVo group = new GroupVo.GroupVoWithAdmin(groupRepository.findById(seq).get());
		return group;
	}

	@Override
	public List<GroupVo> getByAdminUserId(Long seq) {
		List<GroupVo> groupList = new ArrayList<>();
		groupRepository.findByUserSeq(seq).forEach(g -> groupList.add(new GroupVo.GroupVoWithAdmin(g)));
		return groupList;
	}

	@Override
	public void updateById(Long seq, Group group) {

	}

	@Override
	public void deleteById(Long seq) {
		groupRepository.deleteById(seq);
	}
}
