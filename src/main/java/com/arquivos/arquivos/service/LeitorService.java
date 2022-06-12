package com.arquivos.arquivos.service;

import com.arquivos.arquivos.model.CSVModel;
import com.arquivos.arquivos.model.XLSXModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.apache.commons.collections.IteratorUtils.toList;

@Service
@RequiredArgsConstructor
public class LeitorService {

    public String leitorCSV(MultipartFile files) throws IOException {


        Reader reader = new InputStreamReader(files.getInputStream());
        CsvToBean<CSVModel> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CSVModel.class)
                .build();

        List<CSVModel> listaCSV = csvToBean.parse();

        for(CSVModel csv : listaCSV){
            System.out.println(csv);
        }

        return "arquivo-importado";
    }



    public String leitorXLSX(MultipartFile files){

        List <XLSXModel> arquivoXlxs = new ArrayList<>();


        try {

            Workbook workbook = new XSSFWorkbook(files.getInputStream());



            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            //setando as linhas
            List <Row> rows = (List<Row>)toList(sheet.iterator());
            rows.remove(0);

            rows.forEach(row ->{
                //setando as celulas
                List <Cell> cells = (List<Cell>)toList(row.cellIterator());


                //atribuindo os valores
                XLSXModel arquivoBuild = XLSXModel.builder()
                        .nome(cells.get(0).getStringCellValue())
                        .idade((int)cells.get(1).getNumericCellValue())
                        .cidade(cells.get(2).getStringCellValue())
                        .rua(cells.get(3).getStringCellValue())
                        .build();

                arquivoXlxs.add(arquivoBuild);

            });


            for (XLSXModel arquivo : arquivoXlxs){
                System.out.println(arquivo);
            }


        }catch(FileNotFoundException e) {
            System.out.println("arquivo nao encontrado "+e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ok";
    }

}
