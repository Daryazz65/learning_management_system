package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}