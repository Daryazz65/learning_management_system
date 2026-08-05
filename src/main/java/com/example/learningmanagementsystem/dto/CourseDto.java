package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDto(
        Long id,
        @NotBlank(message = "Название курса обязательно") String name,
        String description,
        @NotNull(message = "Преподаватель обязателен") Long teacherId
) {}