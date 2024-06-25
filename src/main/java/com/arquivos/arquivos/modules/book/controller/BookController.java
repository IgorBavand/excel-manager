package com.arquivos.arquivos.modules.book.controller;

import com.arquivos.arquivos.modules.book.dto.BookRequest;
import com.arquivos.arquivos.modules.book.dto.PageRequest;
import com.arquivos.arquivos.modules.book.model.Book;
import com.arquivos.arquivos.modules.book.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Book> saveBook(@RequestBody @Valid BookRequest request) {
        Book book = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateById(@PathVariable Integer id, @RequestBody BookRequest request) {
        return ResponseEntity.ok(service.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllProdutos(PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.getBooks(pageRequest));
    }

}
