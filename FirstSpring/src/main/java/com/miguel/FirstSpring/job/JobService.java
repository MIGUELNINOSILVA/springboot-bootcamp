package com.miguel.FirstSpring.job;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface JobService {
	ResponseEntity<List<Job>> findAll();

	Job findOne(Long idJob);
	
	void createJob(Job job);
	
	Job update(Long id, Job job);
	
	Job delete(Long id);
}
