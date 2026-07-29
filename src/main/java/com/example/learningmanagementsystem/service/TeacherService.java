package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteTeacher(Long id);
}