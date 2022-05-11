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
	private final LocationService locationService;

	@Override
	public GroupVo findBySeq(long seq) {
		Optional<Group> group = groupRepository.findBySeq(seq);
		if (group.isPresent()) {
			return new GroupVo.GroupVoWithAdmin(group.get());
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
	public Long findByAuthenticaionCode(String authenticationCode) {
		Optional<Group> group = groupRepository.findByAuthenticationCode(authenticationCode);
		if (group.isPresent()) {
			return group.get().getSeq();
		}
		return Long.parseLong("-1");
	}

	@Override
	public boolean addGroup(String name, boolean isOpen, String thumbnail, long userSeq, double longtitude,
		double latitude) {
		Group group = Group.builder()
			.name(name)
			.isOpen(isOpen)
			.thumbnail(thumbnail)
			.authenticationCode("TEST")
			.user(User.builder().seq(userSeq).build())
			.longtitude(longtitude)
			.latitude(latitude)
			.build();
		groupRepository.save(group);
		locationService.addLocation(group.getSeq(), group.getName(), longtitude, latitude);
		return true;
	}
}
