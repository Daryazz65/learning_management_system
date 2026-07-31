package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record GroupDto(
        Long id,
        @NotBlank(message = "Название группы обязательно") String name,
        List<Long> studentIds
) {}