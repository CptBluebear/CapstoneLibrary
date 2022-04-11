package org.corodiak.capstonelibrary.repository;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	List<Group> findByUserSeq(Long seq);
	List<Group> findByIsOpenFalse();
}
