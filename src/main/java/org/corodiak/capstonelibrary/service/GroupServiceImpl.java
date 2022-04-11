package org.corodiak.capstonelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.corodiak.capstonelibrary.repository.GroupRepository;
import org.corodiak.capstonelibrary.type.entity.Group;
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
	public List<Group> findAll() {
		List<Group> groupList = new ArrayList<>();
		groupRepository.findAll().forEach(g -> groupList.add(g));
		return groupList;
	}

	@Override
	public List<Group> getPublicGroupList() {
		List<Group> groupList = new ArrayList<>();
		groupRepository.findByIsOpenFalse().forEach(g -> groupList.add(g));
		return groupList;
	}

	@Override
	public Group getById(Long seq) {
		Group group = groupRepository.findById(seq).get();
		return group;
	}

	@Override
	public List<Group> getByAdminUserId(Long seq) {
		List<Group> groupList = groupRepository.findByUserSeq(seq);
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
