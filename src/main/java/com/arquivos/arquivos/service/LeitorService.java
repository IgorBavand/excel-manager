package com.arquivos.arquivos.service;

import com.arquivos.arquivos.model.CSVModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class LeitorService {

    public String leitor(MultipartFile files) throws IOException {


        Reader reader = new InputStreamReader(files.getInputStream());
        CsvToBean<CSVModel> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CSVModel.class)
                .build();

        List<CSVModel> listaCSV = csvToBean.parse();

        for(CSVModel csv : listaCSV){
            System.out.println(csv.getCidade());
        }

        return "arquivo importado com sucesso";
    }
}
