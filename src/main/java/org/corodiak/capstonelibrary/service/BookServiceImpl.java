package org.corodiak.capstonelibrary.service;

import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	/*
	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}
	 */

	@Override
	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(b -> bookList.add(b));
		return bookList;
	}

	@Override
	public Book getById(Long seq) {
		Book book = bookRepository.findById(seq).get();
		return book;
	}

	@Override
	public List<Book> getByUserId(Long seq) {
		List<Book> bookList = bookRepository.findByUserSeq(seq);
		return bookList;
	}

	@Override
	public List<Book> getByGroupId(Long seq) {
		return null;
	}

	@Override
	public void updateById(Long seq, Book book) {
		Optional<Book> e = bookRepository.findById(seq);

		/*if (e.isPresent()) {
			e.get().setSeq(seq);
		}*/
	}

	@Override
	public void deleteById(Long seq) {
		bookRepository.deleteById(seq);
	}

	@Override
	public List<Book> getByKeyword(String keyword) {
		return null;
	}
}
