package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Courses;
import com.example.repository.CoursesRepository;

@Service
public class CoursesService {

	private CoursesRepository coursesRepo;

	@Autowired
	private CoursesService(CoursesRepository coursesRepo) {
		this.coursesRepo = coursesRepo;
	}
	
	public Courses createCourse(Courses course) {
		return coursesRepo.save(course);
	}
	
	public void deleteCourse(int courseId) {
		coursesRepo.deleteById(courseId);
	}

	public List<Courses> getCourses() {
		return coursesRepo.findAll();
	}
	
	public Optional<Courses> getCourseById(int courseId) {
		return coursesRepo.findById(courseId);
	}
	
	public Courses updateCourse(Courses inputCourse) {
		Courses response = new Courses();
		Optional<Courses> existingCourse = coursesRepo.findById(inputCourse.getId());
		if(existingCourse.isPresent()) {
			Courses courseDetails = existingCourse.get();
			courseDetails.setCourseName(inputCourse.getCourseName());
			courseDetails.setCoursePrice(inputCourse.getCoursePrice());
			response = coursesRepo.save(courseDetails);
		}
		// TODO: add error in response
		return response;
	}
	
}
