package org.corodiak.capstonelibrary.controller;

import org.corodiak.capstonelibrary.service.BookService;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

	@Autowired
	BookService bookService;

	@GetMapping("/info")
	public String info() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
		return "info";
	}

	@GetMapping("/admin")
	public String admin() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "admin";
	}

	@GetMapping
	public String index() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "index";
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public String saveBook(
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
		){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getUsername());
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
				//.user(user)
				//.group(group)
				.build();
		bookService.save(book);
		return "/bookList";
	}

	@GetMapping("/bookList")
	public String bookList(){
		List<Book> bookList = new ArrayList<>();
		bookList = bookService.findAll();
		return bookList+"test";
	}
}
