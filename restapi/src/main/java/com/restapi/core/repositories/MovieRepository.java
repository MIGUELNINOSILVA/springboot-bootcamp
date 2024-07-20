package com.restapi.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.core.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
