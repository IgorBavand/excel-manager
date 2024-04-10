package com.arquivos.arquivos.modules.book;

import com.arquivos.arquivos.modules.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
