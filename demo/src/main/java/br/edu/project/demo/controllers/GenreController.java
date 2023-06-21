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

import br.edu.project.demo.models.dto.GenreDTO;
import br.edu.project.demo.models.entity.Genre;
import br.edu.project.demo.models.repository.GenreRepository;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreRepository genreRepository;


    @GetMapping
    public ResponseEntity<Object> getGenres() {
        return ResponseEntity.status(HttpStatus.OK).body(genreRepository.findAll());
    }
    
    
    @PostMapping
    public ResponseEntity<Object> saveGenre(@RequestBody GenreDTO genreDTO) {
        Genre genreEntity = new Genre();
        BeanUtils.copyProperties(genreDTO, genreEntity);

        
        

        return ResponseEntity.status(HttpStatus.OK).body(genreRepository.save(genreEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable Integer id) {
        Optional<Genre> genreEntity = genreRepository.findById(id);

        if(!genreEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre, not found.");
        }
        genreRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Genre has been deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGenre(@PathVariable Integer id, @RequestBody GenreDTO genreDTO) {
       Optional<Genre> genreExists = genreRepository.findById(id);

       if(!genreExists.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre, not found.");
       }
    
       Genre genreEntity = genreExists.get();

       BeanUtils.copyProperties(genreDTO, genreEntity);
       return ResponseEntity.status(HttpStatus.OK).body(genreRepository.save(genreEntity));
    }
    
}
