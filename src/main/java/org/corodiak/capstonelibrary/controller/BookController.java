package org.corodiak.capstonelibrary.controller;

import java.time.LocalDate;
import java.util.List;

import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.BookApiService;
import org.corodiak.capstonelibrary.service.BookLogService;
import org.corodiak.capstonelibrary.service.BookService;
import org.corodiak.capstonelibrary.type.dto.BookInfo;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.corodiak.capstonelibrary.type.vo.BookVo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	private final BookApiService bookApiService;
	private final BookLogService bookLogService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel bookAdd(
		@RequestParam("title") String title,
		@RequestParam("author") String author,
		@RequestParam("publisher") String publisher,
		@RequestParam("isbn") String isbn,
		@RequestParam("thumbnail") String thumbnail,
		@RequestParam("publishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate publishDate,
		@RequestParam("description") String description,
		@RequestParam("category") String category,
		@RequestParam("groupSeq") long groupSeq
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		bookService.addBook(title, author, publisher, isbn, thumbnail, publishDate, description,
			Category.ofCode(category), userSeq, groupSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel bookDelete(
		@PathVariable("seq") long seq
	) {
		bookService.removeBook(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel bookGet(
		@PathVariable("seq") long seq
	) {
		BookVo book = bookService.findBook(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("book", book);
		return responseModel;
	}

	@RequestMapping(value = "/mybook", method = RequestMethod.GET)
	public ResponseModel myBookList(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<BookVo> bookList = bookService.findByUserSeq(userSeq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookList", bookList);
		return responseModel;
	}

	@RequestMapping(value = "/myborrow", method = RequestMethod.GET)
	public ResponseModel myBorrowList(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<BookVo> bookList = bookLogService.findMyBorrow(userSeq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookList", bookList);
		return responseModel;
	}

	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public ResponseModel bookList(
		@RequestParam(name = "groupSeq", required = false, defaultValue = "-1") long groupSeq,
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<BookVo> bookList = null;
		if (groupSeq == -1) {
			bookList = bookService.findAll(start, display);
		} else {
			bookList = bookService.findByGroupSeq(groupSeq, start, display);
		}
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookList", bookList);
		return responseModel;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel bookUpdate(
		@RequestParam("seq") Long seq,
		@RequestParam("title") String title,
		@RequestParam("author") String author,
		@RequestParam("publisher") String publisher,
		@RequestParam("isbn") String isbn,
		@RequestParam("thumbnail") String thumbnail,
		@RequestParam("publishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate publishDate,
		@RequestParam("description") String description,
		@RequestParam("category") String category
	) {
		bookService.updateBook(seq, title, author, publisher, isbn, thumbnail, publishDate, description,
			Category.ofCode(category));
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/borrow", method = RequestMethod.PATCH)
	public ResponseModel borrowBook(
		@RequestParam("userSeq") Long userSeq,
		@RequestParam("bookSeq") Long bookSeq
	) {
		bookService.borrowBook(userSeq, bookSeq);

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/return", method = RequestMethod.PATCH)
	public ResponseModel returnBook(
		@RequestParam("userSeq") Long userSeq,
		@RequestParam("bookSeq") Long bookSeq
	) {
		bookService.returnBook(userSeq, bookSeq);

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/isbn/{isbn}", method = RequestMethod.GET)
	public ResponseModel searchByIsbn(
		@PathVariable("isbn") String isbn
	) {
		BookInfo bookInfo = bookApiService.searchByIsbn(isbn);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookData", bookInfo);
		return responseModel;
	}

	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public ResponseModel bookSearch(
		@RequestParam("keyword") String keyword,
		@RequestParam(name = "category", required = false, defaultValue = "-1") String category
	) {
		List<BookVo> bookList = bookService.searchBook(keyword, category);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookList", bookList);
		return responseModel;
	}
}
