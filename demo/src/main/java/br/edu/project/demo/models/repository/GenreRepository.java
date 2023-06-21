package br.edu.project.demo.models.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.project.demo.models.entity.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer>{
    
}
