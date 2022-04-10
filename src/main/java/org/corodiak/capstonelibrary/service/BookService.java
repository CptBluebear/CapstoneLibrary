package org.corodiak.capstonelibrary.service;

import org.corodiak.capstonelibrary.repository.BookRepository;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public void save(Book book) {
		bookRepository.save(book);
	}

	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(b -> books.add(b));
		return books;
	}

	public Book getById(Long seq) {
		Book book = bookRepository.findById(seq).get();
		return book;
	}

	public List<Book> getByUserId(Long seq) {
		List<Book> bookList = bookRepository.findByUserSeq(seq);
		return bookList;
	}
}
