package com.training.SpringRestMaven.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.SpringRestMaven.model.Mentor;
import com.training.SpringRestMaven.repository.MentorRepository;

@Service
public class MentorServices {

	private MentorRepository repository;

	@Autowired
	public MentorServices(MentorRepository repository) {
		this.repository = repository;
	}

	public Mentor saveMentor(Mentor mentor) {
		Mentor response = repository.save(mentor);
		return response;
	}

	public Optional<Mentor> getMentorById(Long id) {
		Optional<Mentor> response = repository.findById(id);
		return response;
	}

	public Mentor updateMentor(Long id, Mentor mentorUpdated) {
		Optional<Mentor> mentor = repository.findById(id);
		if (mentor.isPresent()) {
			Mentor mentorInfo = mentor.get();
			mentorInfo.setActiveClasses(mentorUpdated.getActiveClasses());

			Mentor response = repository.save(mentorInfo);
			return response;
		} else {
			throw new RuntimeException("Mentor not there");
		}
	}

	public void deleteMentorById(Long id) {
		repository.deleteById(id);
	}

	public List<Mentor> getAllMentors() {
		return repository.findAll();
	}

}
