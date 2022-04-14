package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {
	public void save(BookVo book);
	public List<Book> findByUserSeq(Long seq);
	public List<Book> findAll();
	public Optional<Book> findById(Long seq);
	public long deleteById(Long seq);
}
