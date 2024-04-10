package com.arquivos.arquivos.modules.book.dto;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookImportRequest {

    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "author")
    private String author;
    @CsvBindByName(column = "genre")
    private String genre;
    @CsvBindByName(column = "publication year")
    private Integer publicationYear;
    @CsvBindByName(column = "publisher")
    private String publisher;
    @CsvBindByName(column = "isbn")
    private String isbn;
    @CsvBindByName(column = "pages")
    private Integer pages;
    @CsvBindByName(column = "language")
    private String language;
}
