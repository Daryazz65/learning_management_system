package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.TeacherDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherControllerTest {

    @Test
    void testTeacherDtoFields() {
        TeacherDto dto = new TeacherDto();
        dto.setId(1L);
        dto.setName("Иван");
        dto.setLastName("Иванов");

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Иван", dto.getName());
        assertEquals("Иванов", dto.getLastName());
    }

    @Test
    void testTeacherDtoEquality() {
        TeacherDto dto1 = new TeacherDto();
        dto1.setId(1L);
        dto1.setName("Иван");

        TeacherDto dto2 = new TeacherDto();
        dto2.setId(1L);
        dto2.setName("Иван");

        assertEquals(dto1.getId(), dto2.getId());
        assertEquals(dto1.getName(), dto2.getName());
    }
}