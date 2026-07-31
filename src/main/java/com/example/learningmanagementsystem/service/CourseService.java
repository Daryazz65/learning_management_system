package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.CourseDto;
import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto dto);
    CourseDto getCourseById(Long id);
    List<CourseDto> getAllCourses();
    CourseDto updateCourse(Long id, CourseDto dto);
    void deleteCourse(Long id);
}