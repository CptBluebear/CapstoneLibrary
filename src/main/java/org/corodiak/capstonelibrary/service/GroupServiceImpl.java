package org.corodiak.capstonelibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.GroupRepository;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;

	@Override
	public GroupVo findBySeq(long seq) {
		Optional<Group> group = groupRepository.findBySeq(seq);
		if(group.isPresent()) {
			return new GroupVo(group.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	public List<GroupVo> findOpenGroup() {
		List<Group> groupList = groupRepository.findGroupIsOpen();
		List<GroupVo> results = groupList.stream()
			.map(e -> new GroupVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public boolean removeGroup(long seq) {
		long result = groupRepository.deleteBySeq(seq);
		return result == 1;
	}

	@Override
	public List<GroupVo> findByUserSeq(long userSeq) {
		List<Group> groupList = groupRepository.findByUserSeq(userSeq);
		List<GroupVo> results = groupList.stream()
			.map(e -> new GroupVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	public boolean addGroup(String name, boolean isOpen, String thumbnail, long userSeq) {
		Group group = Group.builder()
			.name(name)
			.isOpen(isOpen)
			.thumbnail(thumbnail)
			.authenticationCode("TEST")
			.user(User.builder().seq(userSeq).build())
			.build();
		groupRepository.save(group);
		return true;
	}
}
