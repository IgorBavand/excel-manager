package com.arquivos.arquivos.modules.excel.enums;

import lombok.AllArgsConstructor;

public enum Extensions {
    CSV("csv"),
    XLSX("xlsx");

    private final String value;

    private Extensions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
