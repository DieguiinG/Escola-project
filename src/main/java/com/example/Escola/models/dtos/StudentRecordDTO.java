package com.example.Escola.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRecordDTO(@NotBlank String name, @NotNull Double age, @NotBlank String course) {

}
