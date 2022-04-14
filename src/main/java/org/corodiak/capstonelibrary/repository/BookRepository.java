package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {
	List<Book> findByUserSeq(Long seq);
	List<Book> findAll();
	Optional<Book> findById(Long seq);
	long deleteById(Long seq);
}
