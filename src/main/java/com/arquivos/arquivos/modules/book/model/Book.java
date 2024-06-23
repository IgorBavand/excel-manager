package com.arquivos.arquivos.modules.book.model;

import com.arquivos.arquivos.modules.book.dto.BookRequest;
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

    public static Book of(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .genre(request.genre())
                .publicationYear(request.publicationYear())
                .publisher(request.publisher())
                .isbn(request.isbn())
                .pages(request.pages())
                .language(request.language())
                .build();
    }

    public void update(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.genre = request.genre();
        this.publicationYear = request.publicationYear();
        this.publisher = request.publisher();
        this.isbn = request.isbn();
        this.pages = request.pages();
        this.language = request.language();
    }

}
