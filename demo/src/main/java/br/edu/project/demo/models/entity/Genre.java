package br.edu.project.demo.models.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ORM
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    
    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name="genre_id"), inverseJoinColumns =
     @JoinColumn(name = "book_id"))
    private java.util.List<Book> books;
    
}
