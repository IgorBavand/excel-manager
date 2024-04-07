package com.arquivos.arquivos.modules.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok().body("Endpoint funcionando");
    }
}
