package com.training.SpringRestMaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.SpringRestMaven.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

}
