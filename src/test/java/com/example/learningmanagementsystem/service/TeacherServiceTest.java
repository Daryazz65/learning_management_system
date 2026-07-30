package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.mapper.TeacherMapper;
import com.example.learningmanagementsystem.mapper.TeacherMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    @Test
    void testTeacherDtoCreation() {
        TeacherDto dto = new TeacherDto();
        dto.setId(1L);
        dto.setName("Иван");
        dto.setLastName("Иванов");

        assertEquals(1L, dto.getId());
        assertEquals("Иван", dto.getName());
        assertEquals("Иванов", dto.getLastName());
    }

    @Test
    void testTeacherEntityCreation() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Иван");
        teacher.setLastName("Иванов");

        assertEquals(1L, teacher.getId());
        assertEquals("Иван", teacher.getName());
        assertEquals("Иванов", teacher.getLastName());
    }

    @Test
    void testTeacherMapper() {
        TeacherMapper mapper = new TeacherMapperImpl();

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Иван");
        teacher.setLastName("Иванов");

        TeacherDto dto = mapper.toDto(teacher);

        assertEquals(1L, dto.getId());
        assertEquals("Иван", dto.getName());
        assertEquals("Иванов", dto.getLastName());
    }
}