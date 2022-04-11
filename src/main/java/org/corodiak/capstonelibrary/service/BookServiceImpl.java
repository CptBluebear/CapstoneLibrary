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

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(b -> books.add(b));
		return books;
	}

	@Override
	public Book getById(Long idx) {
		Book book = bookRepository.findById(idx).get();
		return book;
	}

	@Override
	public List<Book> getByUserId(Long idx) {
		List<Book> bookList = bookRepository.findByUserSeq(idx);
		return bookList;
	}

	@Override
	public List<Book> getByGroupId(Long idx) {
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
		//bookRepository.f
		return null;
	}
}
