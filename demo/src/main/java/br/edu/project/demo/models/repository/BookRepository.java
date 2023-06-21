package br.edu.project.demo.models.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.project.demo.models.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{


}