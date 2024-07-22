package com.miguel.FirstSpring.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miguel.FirstSpring.job.Job;
import com.miguel.FirstSpring.job.JobService;

@Service
public class JobServiceImpl implements JobService {
	private List<Job> jobs = new ArrayList<>();

	@Override
	public ResponseEntity<List<Job>> findAll() {
		if (this.jobs.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(this.jobs);
		}
	}

	@Override
	public void createJob(Job job) {
		Long idGen = Long.valueOf(this.jobs.size() + 1);
		job.setId(idGen);
		jobs.add(job);
	}

	@Override
	public Job findOne(Long idJob) {
		return this.jobs.stream().filter(job -> job.getId().equals(idJob)).findFirst().orElse(null);
	}

	@Override
	public Job update(Long id, Job job) {
		Job jobFound = this.findOne(id);
		if (jobFound != null) {
			BeanUtils.copyProperties(job, jobFound, "id");
			return jobFound;
		}

		return null;
	}

	@Override
	public Job delete(Long id) {
		Job jobFound = this.findOne(id);
		if (jobFound != null) {
			this.jobs.remove(jobFound);
			return jobFound;
		}
		return null;
	}

}
