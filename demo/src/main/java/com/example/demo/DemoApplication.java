package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("<h1>Hello %s!</h1>", name);
	}

	// Useful links
	// https://start.spring.io/

	@GetMapping("/idk")
	public String idk(@RequestParam(value = "name", defaultValue = "HelloWorld") String in) {
		String out = "";
		char c;
		String s;

		for (int i = 0; i < in.length(); i++) {
			if (i % 2 != 0) {
				c = in.charAt(i);
				s = c + "";
				s = s.toUpperCase();
				out += s;
			} else {
				c = in.charAt(i);
				s = c + "";
				s = s.toLowerCase();
				out += s;
			}
		}

		return String.format("<h1>Out: %s</h1>", out);
	}
}
