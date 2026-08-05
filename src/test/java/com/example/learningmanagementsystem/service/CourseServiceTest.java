package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.CourseDto;
import com.example.learningmanagementsystem.entity.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Test
    void testCourseDtoCreation() {
        CourseDto dto = new CourseDto(1L, "Java", "Основы Java", 1L);

        assertEquals(1L, dto.id());
        assertEquals("Java", dto.name());
        assertEquals("Основы Java", dto.description());
        assertEquals(1L, dto.teacherId());
    }

    @Test
    void testCourseEntityCreation() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");

        assertEquals(1L, course.getId());
        assertEquals("Java", course.getName());
    }
}