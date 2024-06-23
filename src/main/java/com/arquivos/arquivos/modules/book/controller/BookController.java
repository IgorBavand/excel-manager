package com.arquivos.arquivos.modules.book.controller;

import com.arquivos.arquivos.modules.book.dto.BookRequest;
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

    @PostMapping("/save-book")
    public Book saveBook(@RequestBody BookRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
        public Book findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Book updateById(@PathVariable Integer id, @RequestBody BookRequest request) {
        return service.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    public Book deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<Book> getBooks() {
        return service.getBooks();
    }
}
