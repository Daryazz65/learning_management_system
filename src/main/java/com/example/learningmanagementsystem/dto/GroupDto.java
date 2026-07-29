package com.example.learningmanagementsystem.dto;

import lombok.Data;
import java.util.List;

@Data
public class GroupDto {
    private Long id;
    private String name;
    private List<Long> studentIds;
}