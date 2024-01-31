package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

	@GetMapping
	public String homeController() {
		return "Welcome back to home";
	}
}
