package com.example.learningmanagementsystem.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Курс не найден с id: " + id);
    }
}