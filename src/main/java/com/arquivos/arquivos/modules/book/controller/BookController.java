package com.arquivos.arquivos.modules.book.controller;

import com.arquivos.arquivos.modules.book.model.Book;
import com.arquivos.arquivos.modules.book.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importBooks(Integer colunmId,
                            @Parameter(description = "File to upload") @RequestPart(value = "file")
                            @Schema(type = "string", format = "binary") MultipartFile file) {
        service.importBook(colunmId, file);
    }

    @GetMapping
    public List<Book> getBooks() {
        return service.getBooks();
    }
}
