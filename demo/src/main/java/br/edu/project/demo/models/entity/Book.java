package br.edu.project.demo.models.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

// ORM
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String title;
    private String description;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;
    
    @JsonManagedReference
    @ManyToMany (mappedBy = "books",fetch = FetchType.LAZY)
    private List<Genre> genres = new ArrayList<Genre>();

}