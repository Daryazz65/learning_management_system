package com.example.learningmanagementsystem.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Long id) {
        super("Группа не найдена с id: " + id);
    }
}