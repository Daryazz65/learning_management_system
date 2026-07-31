package com.example.learningmanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ScheduleDto(
        Long id,
        @NotNull(message = "Группа обязательна") Long groupId,
        @NotNull(message = "Курс обязателен") Long courseId,
        @NotNull(message = "Преподаватель обязателен") Long teacherId,
        @NotNull(message = "Дата начала обязательна") LocalDateTime dateStart,
        @NotNull(message = "Дата окончания обязательна") LocalDateTime dateFinish
) {}