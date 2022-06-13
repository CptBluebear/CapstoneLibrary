package org.corodiak.capstonelibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.repository.GroupUserRepository;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.GroupUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.corodiak.capstonelibrary.type.vo.UserVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupUserServiceImpl implements GroupUserService {

	private final GroupUserRepository groupUserRepository;
	private final BookRepository bookRepository;

	@Override
	@Transactional
	public boolean addGroupUser(Long groupSeq, Long userSeq) {
		GroupUser groupUser = GroupUser.builder()
			.group(Group.builder().seq(groupSeq).build())
			.user(User.builder().seq(userSeq).build())
			.build();
		groupUserRepository.save(groupUser);
		return true;
	}

	@Override
	@Transactional
	public boolean removeGroupUser(Long userSeq, Long groupSeq) {
		Long result1 = groupUserRepository.deleteByUserSeqAndGroupSeq(userSeq, groupSeq);
		Long result2 = bookRepository.deleteByUserSeqAndGroupSeq(userSeq, groupSeq);
		return result1 == 1;
	}

	@Override
	@Transactional
	public boolean findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq) {
		Optional<GroupUser> groupUser = groupUserRepository.findByUserSeqAndGroupSeq(userSeq, groupSeq);
		return groupUser.isPresent();
	}

	@Override
	public List<UserVo> findUserByGroupSeq(Long groupSeq) {
		List<GroupUser> groupUserList = groupUserRepository.findUserByGroupSeq(groupSeq);
		List<UserVo> results = new ArrayList<>();
		for (GroupUser gu : groupUserList) {
			results.add(new UserVo(gu.getUser()));
		}
		return results;
	}

	@Override
	public boolean checkUserIsSignedGroup(Long userSeq, Long groupSeq) {
		Optional<GroupUser> groupUser = groupUserRepository.findByUserSeqAndGroupSeq(userSeq, groupSeq);
		return groupUser.isPresent();
	}

	@Override
	public List<GroupVo> findByUserSeq(Long userSeq) {
		List<GroupUser> groupUserList = groupUserRepository.findByUserSeq(userSeq);
		List<GroupVo> results = groupUserList.stream()
			.map(e -> new GroupVo(e.getGroup()))
			.collect(Collectors.toList());
		return results;
	}
}
