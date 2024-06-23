package com.arquivos.arquivos.modules.excel.service;

import com.arquivos.arquivos.modules.excel.dto.ColumnRequest;
import com.arquivos.arquivos.modules.excel.model.Column;
import com.arquivos.arquivos.modules.excel.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository repository;

    public Column findById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("not found")
        );
    }

    public List<Column> findAll() {
        return repository.findAll();
    }

    public void save(ColumnRequest request) {
        var colunm = Column.builder()
            .header(request.header())
            .description(request.description())
            .delimiter(request.delimiter())
            .build();

        repository.save(colunm);
    }
}
