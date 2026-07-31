package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;

public record TeacherDto(
        Long id,
        @NotBlank(message = "Имя обязательно") String name,
        @NotBlank(message = "Фамилия обязательна") String lastName
) {}