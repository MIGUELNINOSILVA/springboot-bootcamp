package com.miguel.FirstSpring.job;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface JobService {
	List<Job> findAll();

	Job findOne(Long idJob);
	
	void createJob(Job job);
	
	boolean update(Long id, Job job);
	
	boolean delete(Long id);
}
