package com.example.learningmanagementsystem.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String lastName;
    private List<Long> groupIds;
}