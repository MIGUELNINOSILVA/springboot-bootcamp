package com.restapi.core.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.restapi.core.models.Movie;
import com.restapi.core.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping
	public List<Movie> getAll() {
		return this.movieRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getOne(@PathVariable Long id) {
		Optional<Movie> movie = this.movieRepository.findById(id);
		return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Movie> create(@RequestBody Movie movie) {
		Movie savedMovie = this.movieRepository.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!this.movieRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		this.movieRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody() Movie movie) {
		if (!this.movieRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		movie.setId(id);
		Movie savedMovide =  this.movieRepository.save(movie);
		return ResponseEntity.ok(savedMovide);
	}
}
