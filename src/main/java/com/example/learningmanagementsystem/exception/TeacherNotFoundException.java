package com.example.learningmanagementsystem.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super("Преподаватель не найден с id: " + id);
    }
}