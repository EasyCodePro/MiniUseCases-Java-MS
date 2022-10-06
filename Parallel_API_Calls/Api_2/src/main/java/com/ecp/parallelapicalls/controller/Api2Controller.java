package com.ecp.parallelapicalls.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api2Controller {

	@GetMapping("/api2")
	public String api1() throws InterruptedException {
		Thread.sleep(4000);//4 seconds
		return " world from ";
	}
	
}
