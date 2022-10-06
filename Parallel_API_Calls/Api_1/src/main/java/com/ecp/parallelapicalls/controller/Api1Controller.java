package com.ecp.parallelapicalls.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api1Controller {
	
	@GetMapping("/api1")
	public String api1() throws InterruptedException {
		Thread.sleep(6000);//6 seconds
		return "Hello";
	}
	
}
