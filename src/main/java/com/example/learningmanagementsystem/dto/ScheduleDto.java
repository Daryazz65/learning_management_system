package com.example.learningmanagementsystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleDto {
    private Long id;
    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private LocalDateTime dateStart;
    private LocalDateTime dateFinish;
}