package br.edu.project.demo.models.dto;


import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String title;
    private String description;
    private Integer authorId;
    private List<Integer> genres;
    
}
