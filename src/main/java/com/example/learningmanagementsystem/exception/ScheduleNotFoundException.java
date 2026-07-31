package com.example.learningmanagementsystem.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("Расписание не найдено с id: " + id);
    }
}