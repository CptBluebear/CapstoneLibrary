package org.corodiak.capstonelibrary.controller;

import org.corodiak.capstonelibrary.repository.LibraryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	LibraryPointRepository repository;

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

	@GetMapping("/locsave")
	public String save() {
		repository.save(33, "testloc", 127.038641, 37.297775);
		return "save";
	}

	@GetMapping("/locfind")
	public String find() {
		String data = repository.findByLocationAndDistance(127.038641, 37.297775, 100).toString();
		System.out.println(data);
		return data;
	}
}
