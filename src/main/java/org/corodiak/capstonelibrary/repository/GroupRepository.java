package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Group;

public interface GroupRepository {

	Group save(Group group);

	long deleteBySeq(Long seq);

	Optional<Group> findBySeq(Long seq);

	List<Group> findByUserSeq(Long userSeq);

	List<Group> findGroupIsOpen();

	Optional<Group> findByAuthenticationCode(String authenticationCode);

	Long authorizeAdmin(Long groupSeq, Long userSeq);
}
