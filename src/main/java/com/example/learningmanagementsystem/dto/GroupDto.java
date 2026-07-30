package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class GroupDto {
    private Long id;

    @NotBlank(message = "Название группы обязательно")
    private String name;

    private List<Long> studentIds;
}