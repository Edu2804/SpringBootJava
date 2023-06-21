package br.edu.project.demo.models.entity;

import javax.persistence.*;

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
@Table(name = "author")
public class Author {
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String name;
    
}
