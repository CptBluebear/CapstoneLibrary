package org.corodiak.capstonelibrary.repository;

import org.corodiak.capstonelibrary.type.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
