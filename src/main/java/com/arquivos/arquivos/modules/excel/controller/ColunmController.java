package com.arquivos.arquivos.modules.excel.controller;

import com.arquivos.arquivos.modules.excel.dto.ColumnRequest;
import com.arquivos.arquivos.modules.excel.model.Column;
import com.arquivos.arquivos.modules.excel.service.ColumnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/columns")
public class ColunmController {

    private final ColumnService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid ColumnRequest request) {
        service.save(request);
    }

    @GetMapping
    public List<Column> getAll() {
        return service.findAll();
    }
}
