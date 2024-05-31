package com.training.SpringRestMaven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.SpringRestMaven.model.Mentor;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringRestMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestMavenApplication.class, args);
	}

	@GetMapping
	public void getData() {
	}
	
	@PostMapping
	public String postData() {
		return "Hello Spring";
	}
	
	@PutMapping
	public Map<String, String> putData() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "mei");
		map.put("class", "java");
		return map;
	}
	
	@DeleteMapping
	public List<Map<String, String>> deteData() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "mei");
		map.put("class", "java");
		
		List<Map<String, String>> li = new ArrayList<>();
		li.add(map);
		li.add(map);
		return li;
	}
	
	@GetMapping("getData")
	public List<Mentor> getMentors() {
		Mentor mentorOne = new Mentor();
		mentorOne.setId(1000);
		mentorOne.setName("Mei");
		mentorOne.setContact("9080335079");
		mentorOne.setEmail("test@gmail.com");
		mentorOne.setActiveClasses(1);
		
		List<Mentor> mentors = new ArrayList<>();
		mentors.add(mentorOne);
		mentors.add(mentorOne);
		mentors.add(mentorOne);
		return mentors;
	}
	
}
