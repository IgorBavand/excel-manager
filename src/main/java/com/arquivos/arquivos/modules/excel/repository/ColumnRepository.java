package com.arquivos.arquivos.modules.excel.repository;

import com.arquivos.arquivos.modules.excel.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, Integer> {
}
