package com.arquivos.arquivos.modules.excel.dto;

import jakarta.validation.constraints.NotEmpty;

public record ColumnRequest(@NotEmpty String header, @NotEmpty String description, @NotEmpty String delimiter) { }
