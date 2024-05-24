package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
