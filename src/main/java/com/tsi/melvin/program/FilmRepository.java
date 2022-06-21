package com.tsi.melvin.program;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FilmRepository extends CrudRepository<Film, Integer>{

    List<Film> findByTitle(String title);

}
