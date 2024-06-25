package com.arquivos.arquivos.modules.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(

    @NotBlank
    String title,
    @NotBlank
    String author,
    @NotBlank
    String genre,
    @NotNull
    Integer publicationYear,
    @NotBlank
    String publisher,
    @NotBlank
    String isbn,
    @NotNull
    Integer pages,
    @NotBlank
    String language
) {}
