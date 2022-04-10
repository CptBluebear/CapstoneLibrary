package org.corodiak.capstonelibrary.controller;

import org.corodiak.capstonelibrary.service.BookService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.entity.Book;
import org.corodiak.capstonelibrary.type.etc.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {

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
}
