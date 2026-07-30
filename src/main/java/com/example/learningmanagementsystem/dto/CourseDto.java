package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseDto {
    private Long id;

    @NotBlank(message = "Название курса обязательно")
    private String name;

    private String description;

    @NotNull(message = "Преподаватель обязателен")
    private Long teacherId;
}