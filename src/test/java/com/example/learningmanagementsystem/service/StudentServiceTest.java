package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.StudentDto;
import com.example.learningmanagementsystem.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void testStudentDtoCreation() {
        StudentDto dto = new StudentDto(1L, "Анна", "Смирнова", null);

        assertEquals(1L, dto.id());
        assertEquals("Анна", dto.name());
        assertEquals("Смирнова", dto.lastName());
    }

    @Test
    void testStudentEntityCreation() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Анна");
        student.setLastName("Смирнова");

        assertEquals(1L, student.getId());
        assertEquals("Анна", student.getName());
        assertEquals("Смирнова", student.getLastName());
    }
}