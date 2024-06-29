package com.arquivos.arquivos.modules.authentication.controller;

import com.arquivos.arquivos.modules.authentication.dto.RoleRequest;
import com.arquivos.arquivos.modules.authentication.model.Role;
import com.arquivos.arquivos.modules.authentication.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Role> save(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok().body(service.save(request));
    }

}
