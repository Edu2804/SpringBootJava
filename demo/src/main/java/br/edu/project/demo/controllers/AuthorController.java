package br.edu.project.demo.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

import br.edu.project.demo.models.dto.AuthorDTO;
import br.edu.project.demo.models.entity.Author;
import br.edu.project.demo.models.entity.Genre;
import br.edu.project.demo.models.repository.AuthorRepository;
import ch.qos.logback.core.status.Status;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<Object> getAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Object> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        Author authorEntity = new Author();
        BeanUtils.copyProperties(authorDTO, authorEntity);

        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.save(authorEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable Integer id) {
        Optional<Author> authorEntity = authorRepository.findById(id);

        if(!authorEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author has not found  in our database");
        }
        authorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Author has been deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO) {
        Optional<Author> authorExists = authorRepository.findById(id);

        if(!authorExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author has not found  in our database");
        }
        Author authorEntity = authorExists.get();
        BeanUtils.copyProperties(authorDTO, authorEntity);
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.save(authorEntity));



    }

    
}
