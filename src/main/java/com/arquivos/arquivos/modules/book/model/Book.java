package com.arquivos.arquivos.modules.book.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String genre;
    @Column(name = "publication_year")
    private Integer publicationYear;
    private String publisher;
    private String isbn;
    private Integer pages;
    private String language;
}
