package br.edu.project.demo.models.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.project.demo.models.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer>{
    
}
