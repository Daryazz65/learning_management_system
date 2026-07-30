package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleDto {
    private Long id;

    @NotNull(message = "Группа обязательна")
    private Long groupId;

    @NotNull(message = "Курс обязателен")
    private Long courseId;

    @NotNull(message = "Преподаватель обязателен")
    private Long teacherId;

    @NotNull(message = "Дата начала обязательна")
    private LocalDateTime dateStart;

    @NotNull(message = "Дата окончания обязательна")
    private LocalDateTime dateFinish;
}