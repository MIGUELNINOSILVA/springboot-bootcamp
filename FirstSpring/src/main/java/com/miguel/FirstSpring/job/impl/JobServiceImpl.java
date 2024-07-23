package com.miguel.FirstSpring.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miguel.FirstSpring.job.Job;
import com.miguel.FirstSpring.job.JobRespository;
import com.miguel.FirstSpring.job.JobService;

@Service
public class JobServiceImpl implements JobService {
	//private List<Job> jobs = new ArrayList<>();
	JobRespository jobRepository;
	
	

	public JobServiceImpl(JobRespository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		return this.jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		this.jobRepository.save(job);
	}

	@Override
	public Job findOne(Long idJob) {
		return this.jobRepository.findById(idJob).orElse(null);
	}

	@Override
	public boolean update(Long id, Job job) {
		Optional<Job> jobOptional = this.jobRepository.findById(id);
		if (jobOptional.isPresent()) {
			BeanUtils.copyProperties(job, jobOptional, "id");
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Job> jobOptional = this.jobRepository.findById(id);
		if (jobOptional.isPresent()) {
			this.jobRepository.deleteById(id);
			return true;
		}
		return false; 		
	}

}
