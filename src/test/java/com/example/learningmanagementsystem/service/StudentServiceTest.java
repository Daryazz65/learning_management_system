package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.StudentDto;
import com.example.learningmanagementsystem.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void testStudentDtoCreation() {
        StudentDto dto = new StudentDto();
        dto.setId(1L);
        dto.setName("Анна");
        dto.setLastName("Смирнова");

        assertEquals(1L, dto.getId());
        assertEquals("Анна", dto.getName());
        assertEquals("Смирнова", dto.getLastName());
    }

    @Test
    void testStudentEntityCreation() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Анна");
        student.setLastName("Смирнова");

        assertEquals(1L, student.getId());
        assertEquals("Анна", student.getName());
    }
}