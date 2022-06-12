package com.arquivos.arquivos.controller;

import com.arquivos.arquivos.service.LeitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/leitor")
@RequiredArgsConstructor
public class LeitorDeArquivoController {

    private final LeitorService leitorService;

    @PostMapping(value = "/importador/salvar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> leitor(@RequestParam MultipartFile files) throws IOException {
        return ResponseEntity.ok().body(leitorService.leitor(files));
    }

}
