package org.corodiak.capstonelibrary.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
