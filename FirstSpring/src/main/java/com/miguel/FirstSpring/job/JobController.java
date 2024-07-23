package com.miguel.FirstSpring.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping()
	public List<Job> findAll() {
		return this.jobService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getOne(@PathVariable(name = "id") Long id) {

		Job job = this.jobService.findOne(id);
		if (job != null) {
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	public String create(@RequestBody Job job) {
		this.jobService.createJob(job);
		return "Job added successfully";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Job> delete(@PathVariable(name = "id") Long id){
		boolean jobDeleted = this.jobService.delete(id);
		if (jobDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Job> update(@PathVariable(name = "id") Long id, @RequestBody Job job) {
		boolean jobUpdated = this.jobService.update(id, job);
		if (jobUpdated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
