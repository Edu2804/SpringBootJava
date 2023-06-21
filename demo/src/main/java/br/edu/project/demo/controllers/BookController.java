package br.edu.project.demo.controllers;



import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import br.edu.project.demo.models.dto.BookDTO;
import br.edu.project.demo.models.entity.Author;
import br.edu.project.demo.models.entity.Book;
import br.edu.project.demo.models.entity.Genre;
import br.edu.project.demo.models.repository.AuthorRepository;
import br.edu.project.demo.models.repository.BookRepository;
import br.edu.project.demo.models.repository.GenreRepository;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookrepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;


    @GetMapping
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookrepository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody BookDTO bookDTO) {

        Optional<Author> bookAuthor = authorRepository.findById(bookDTO.getAuthorId());

        if(!bookAuthor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This author does not exist in our data base");
        }
        Book newBook = new Book();
        BeanUtils.copyProperties(bookDTO, newBook);
        newBook.setAuthor(bookAuthor.get());

        for(Integer genreId : bookDTO.getGenres()) {
            Optional<Genre> genreBook = genreRepository.findById(genreId);

            if(!genreBook.isPresent()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("genre: " + genreId + " not found");
            }
            newBook.getGenres().add(genreBook.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookrepository.save(newBook));
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer id) {
        Optional<Book> bookExists = bookrepository.findById(id);

        if(!bookExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("book not found in our database");
        }
        bookrepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("book has been deleted");
        
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        Optional<Book> bookExists = bookrepository.findById(id);

        if(!bookExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("book not found in our database");
        }
        Book bookEntity = bookExists.get();
        BeanUtils.copyProperties(bookDTO, bookEntity);
        return ResponseEntity.status(HttpStatus.OK).body(bookrepository.save(bookEntity));
    }
    
}
