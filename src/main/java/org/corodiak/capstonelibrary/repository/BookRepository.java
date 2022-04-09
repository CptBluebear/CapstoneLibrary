package org.corodiak.capstonelibrary.repository;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
