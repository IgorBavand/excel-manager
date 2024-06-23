package com.arquivos.arquivos.modules.book.dto;

public record BookRequest(

    String title,
    String author,
    String genre,
    Integer publicationYear,
    String publisher,
    String isbn,
    Integer pages,
    String language

) {}
