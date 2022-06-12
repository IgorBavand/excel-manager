package com.arquivos.arquivos.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XLSXModel {

    private String nome;
    private Integer idade;
    private String cidade;
    private String rua;
}
