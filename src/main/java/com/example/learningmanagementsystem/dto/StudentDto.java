package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record StudentDto(
        Long id,
        @NotBlank(message = "Имя обязательно") String name,
        @NotBlank(message = "Фамилия обязательна") String lastName,
        List<Long> groupIds
) {}