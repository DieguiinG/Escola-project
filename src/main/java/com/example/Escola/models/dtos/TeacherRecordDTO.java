package com.example.Escola.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record TeacherRecordDTO(@NotBlank String name,@NotBlank String course, @NotBlank String classroom) {

}
