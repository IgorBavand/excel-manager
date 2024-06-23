package com.arquivos.arquivos.modules.book.service;

import com.arquivos.arquivos.modules.book.BooksRepository;
import com.arquivos.arquivos.modules.book.dto.BookImportRequest;
import com.arquivos.arquivos.modules.book.dto.BookRequest;
import com.arquivos.arquivos.modules.book.model.Book;
import com.arquivos.arquivos.modules.excel.enums.Extensions;
import com.arquivos.arquivos.modules.excel.model.Column;
import com.arquivos.arquivos.modules.excel.service.ColumnService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final ColumnService colunmService;
    private final BooksRepository repository;

    public void importBook(Integer columnId, MultipartFile file) {
        Column column = colunmService.findById(columnId);

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException("Invalid file name");
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (Extensions.CSV.getValue().equals(fileExtension)) {
            importCsv(column, file);
        }
        else if (Extensions.XLSX.getValue().equals(fileExtension)) {
            importXlsx(column, file);
        }
        else {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }

    }

    public void importCsv(Column column, MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<BookImportRequest> books = new CsvToBeanBuilder<BookImportRequest>(reader)
                .withType(BookImportRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(column.getDelimiter().charAt(0))
                .build()
                .parse();

            List<Book> booksToSave = books.stream()
                .map(book -> Book.builder()
                    .title(book.getTitle())
                    .isbn(book.getIsbn())
                    .pages(book.getPages())
                    .genre(book.getGenre())
                    .author(book.getAuthor())
                    .language(book.getLanguage())
                    .publicationYear(book.getPublicationYear())
                    .publisher(book.getPublisher())
                    .build())
                .toList();

            repository.saveAll(booksToSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void importXlsx(Column colunm, MultipartFile file) {

        //validationHeader(colunm, file);
        //TODO
        //TODO
        }

    private void validationHeader(Column column, String header) {
        String[] headerColumn = column.getHeader().split(";");
        String[] headers = header.replaceAll("\\[", "").replaceAll("]", "").split(";");

        if (headers.length != headerColumn.length) {
            throw new RuntimeException("The number of headers is not valid.");
        }

        if (!Arrays.equals(headerColumn, headers)) {
            throw new RuntimeException("The header does not match.");
        }
    }

    @Transactional
    public Book save(BookRequest request) {
        return repository.save(Book.of(request));
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book findById(Integer id) {
        Optional<Book> bookOpt = repository.findById(id);
        return bookOpt.orElse(null);
    }

    public Book deleteById(Integer id) {
        Optional<Book> bookOpt = repository.findById(id);
        if(bookOpt.isPresent()) {
            repository.deleteById(id);
        }
        return null;
    }

    @Transactional
    public Book updateById(Integer id, BookRequest request) {
        Optional<Book> bookOpt = repository.findById(id);
        if(bookOpt.isPresent()) {
            bookOpt.get().update(request);
            return bookOpt.get();
        }
        return null;
    }

}
