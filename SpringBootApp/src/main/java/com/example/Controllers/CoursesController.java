package com.example.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Courses;
import com.example.services.CoursesService;

@RestController
@RequestMapping("/api/Courses")
public class CoursesController {
	
	private CoursesService coursesService;
	
	@Autowired
	public CoursesController(CoursesService coursesService) {
		this.coursesService = coursesService;
	}
	
	@GetMapping("/getAll")
	public List<Courses> getCourses() {
		return coursesService.getCourses();
	}
	
}
