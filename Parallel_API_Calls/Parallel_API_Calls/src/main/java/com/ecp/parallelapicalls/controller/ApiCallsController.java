package com.ecp.parallelapicalls.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiCallsController {
	
	@GetMapping("/parallelApiCall")
	public Map<String, Object> api1() throws InterruptedException, ExecutionException {
		RestTemplate restTemplate = new RestTemplate();
		StopWatch st = new StopWatch();
		st.start();
	
		//RestTemplate call 1
		CompletableFuture<ResponseEntity<String>> api1response = CompletableFuture.
        supplyAsync(() -> restTemplate.
				exchange("http://localhost:8181/api1",HttpMethod.GET ,null, String.class));
		
		System.out.println("\n****** CodeCheck001 ********\n");
		
		//RestTemplate call 2
		CompletableFuture<ResponseEntity<String>> api2response = CompletableFuture.
		        supplyAsync(() -> restTemplate.
						exchange("http://localhost:8182/api2",HttpMethod.GET ,null, String.class));
		
		System.out.println("\n****** CodeCheck002 ********\n");
		
		String response1 = "";
		String response2 = "";
		if(api1response.get().getStatusCode().equals(HttpStatus.OK)) {
			response1 = api1response.get().getBody();
		}
		System.out.println("\n****** CodeCheck003 ********\n");
		
		if(api2response.get().getStatusCode().equals(HttpStatus.OK)) {
			response2 = api2response.get().getBody();
		}
		System.out.println("\n****** CodeCheck004 ********\n");
		st.stop();
		
		Map<String,Object> response = new HashMap<>();
		System.out.println("\n****** CodeCheck005 ********\n");
		response.put("finalString", response1 + response2);
		response.put("totalTimeTaken",st.getTotalTimeMillis());

		return response;
	}
	
}
