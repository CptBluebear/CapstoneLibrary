package org.corodiak.capstonelibrary.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.corodiak.capstonelibrary.repository.GroupUserRepository;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.entity.GroupUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupUserServiceImpl implements GroupUserService {

	private final GroupUserRepository groupUserRepository;

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
		Long result = groupUserRepository.deleteByUserSeqAndGroupSeq(userSeq, groupSeq);
		return result == 1;
	}

	@Override
	@Transactional
	public boolean findByUserSeqAndGroupSeq(Long userSeq, Long groupSeq) {
		Optional<GroupUser> groupUser = groupUserRepository.findByUserSeqAndGroupSeq(userSeq, groupSeq);
		return groupUser.isPresent();
	}
}
