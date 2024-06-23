package com.arquivos.arquivos.modules.excel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "columns")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @jakarta.persistence.Column(columnDefinition = "text")
    private String header;
    private String description;
    private String delimiter;
}
