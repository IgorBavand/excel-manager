package com.arquivos.arquivos.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer id) {
        super("O livro com id " +id+ " não foi encontrado.");
    }

}
