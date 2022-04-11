package org.corodiak.capstonelibrary.service;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.Book;

public interface BookService {
	public void save(Book book);

	public List<Book> findAll();

	public Book getById(Long seq);

	public List<Book> getByUserId(Long seq);

	public List<Book> getByGroupId(Long seq);

	public void updateById(Long seq, Book book);

	public void deleteById(Long seq);

	public List<Book> getByKeyword(String keyword);
}
