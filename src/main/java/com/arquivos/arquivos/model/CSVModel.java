package com.arquivos.arquivos.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CSVModel {

    @CsvBindByName(column = "nome")
    private String nome;

    @CsvBindByName(column = "idade")
    private Integer idade;

    @CsvBindByName(column = "cidade")
    private String cidade;

    @CsvBindByName(column = "rua")
    private String rua;



}
