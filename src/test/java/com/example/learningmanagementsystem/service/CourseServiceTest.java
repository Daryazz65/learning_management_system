package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.CourseDto;
import com.example.learningmanagementsystem.entity.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Test
    void testCourseDtoCreation() {
        CourseDto dto = new CourseDto();
        dto.setId(1L);
        dto.setName("Java");
        dto.setDescription("Основы Java");
        dto.setTeacherId(1L);

        assertEquals(1L, dto.getId());
        assertEquals("Java", dto.getName());
        assertEquals(1L, dto.getTeacherId());
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