package com.training.SpringRestMaven.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.SpringRestMaven.model.Mentor;
import com.training.SpringRestMaven.service.MentorServices;

@RestController
@RequestMapping("/mentors")
public class MentorController {
	private MentorServices mentorServices;

//	@Autowired
//	public MentorController(MentorServices mentorServices) {
//		this.mentorServices = mentorServices;
//	}

	@GetMapping("/getAll")
	public Map<String, Object> getAllMentors() {
		List<Mentor> mentorsList = new ArrayList<>();
		// call service layer to get data

		Map<String, Object> result = new HashMap<>();
		result.put("mentors", mentorsList);
		if (mentorsList.isEmpty()) {
			result.put("error", "No mentors found");
		}
		return result;
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Mentor> getMentorInfo(@PathVariable Long id) {
		Optional<Mentor> mentorInfo = Optional.empty();
		// need to call service

		return mentorInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/create")
	public ResponseEntity<Mentor> createMentor(@RequestBody Mentor inputPayload) {
		Mentor mentor = new Mentor();
		// call service to store mentor info

		return ResponseEntity.ok(mentor);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @RequestBody Mentor inputPayload) {
		Mentor mentor = new Mentor();
		// call service to update mentor info

		return ResponseEntity.ok(mentor);
	}

	@PutMapping("/delete/{id}")
	public ResponseEntity<String> deleteMentor(@PathVariable Long id) {
		// call service to delete mentor

		return ResponseEntity.ok("Mentor deleted successfully");
	}

}
