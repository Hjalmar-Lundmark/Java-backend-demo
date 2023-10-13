package com.example.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	Model m;

	public DemoApplication() {
		m = new Model("story.json");
	}

	public static void main(String[] args) {
		DemoApplication app = new DemoApplication();
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("<h1>Hello %s!</h1>", name);
	}

	// Useful links
	// https://start.spring.io/

	@GetMapping("/story")
	public String story(@RequestParam(value = "id", defaultValue = "0") String in) {
		int id = Integer.parseInt(in);
		String all = "";
		JsonObject scene = m.getScene(id);
		String text = scene.get("text").getAsString();
		all += text + "<br><br>";

		JsonArray options = m.getScene(id).get("options").getAsJsonArray();
		if (options.isEmpty()) {
			return all;
		}
		for (int i = 0; i < 2; i++) {
			JsonObject option = options.get(i).getAsJsonObject();
			String optionText = option.get("msg").getAsString();
			int nextId = option.get("nextId").getAsInt();

			all += ("<a href=\"story?id=" + nextId + "\">" + (i+1) + ". " + optionText + "</a><br>");
		}

		return String.format(all);
	}

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
