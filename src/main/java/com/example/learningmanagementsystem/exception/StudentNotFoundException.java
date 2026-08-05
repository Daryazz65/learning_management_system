package com.example.learningmanagementsystem.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Студент не найден с id: " + id);
    }
}