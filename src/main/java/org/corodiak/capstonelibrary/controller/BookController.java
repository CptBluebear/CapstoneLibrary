package org.corodiak.capstonelibrary.controller;

import java.time.LocalDate;
import java.util.List;

import org.corodiak.capstonelibrary.service.BookServiceImpl;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	BookServiceImpl bookService;

	/*
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseModel saveBook(
		@RequestParam(value = "title") String title,
		@RequestParam(value = "author") String author,
		@RequestParam(value = "publisher") String publisher,
		@RequestParam(value = "isbn", required = false) String isbn,
		@RequestParam(value = "code", required = false) String code,
		@RequestParam(value = "thumbnail") String thumbnail,
		@RequestParam(value = "publishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate publishDate,
		@RequestParam(value = "description") String description,
		@RequestParam(value = "category") String category
		//@RequestParam(value = "user") User user,
		//@RequestParam(value = "group") Group group
	) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Book book = Book.builder()
			.title(title)
			.author(author)
			.publisher(publisher)
			.isbn(isbn)
			.code(code)
			.thumbnail(thumbnail)
			.publishDate(publishDate)
			.description(description)
			.category(Category.ofCode(category))
			//.user(user.getUsername())
			//.group(group)
			.build();
		bookService.save(book);

		ResponseModel responseModel = ResponseModel.builder().build();

		return responseModel;
	}
	*/

	@RequestMapping(value = "/book/list", method = RequestMethod.GET)
	public ResponseModel getAllBookList() {
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("도서 목록", bookService.findAll());

		return responseModel;
	}

	@RequestMapping(value = "/book/{idx}", method = RequestMethod.GET)
	public ResponseModel getBook(@PathVariable(value = "idx") Long idx) {
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("도서 정보", bookService.getById(idx));

		return responseModel;
	}

	@RequestMapping(value = "/book/mybook", method = RequestMethod.GET)
	public ResponseModel getMyBookList() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Book> bookList = bookService.getByUserId(Long.valueOf(user.getUsername()));

		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("도서 정보", bookList);

		return responseModel;
	}

	@RequestMapping(value = "/book/{idx}", method = RequestMethod.DELETE)
	public ResponseModel deleteBook(@PathVariable(value = "idx") Long idx) {
		bookService.getById(idx);
		bookService.deleteById(idx);

		ResponseModel responseModel = ResponseModel.builder().build();

		return responseModel;
	}
}
