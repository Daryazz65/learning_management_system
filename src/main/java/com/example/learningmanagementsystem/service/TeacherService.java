package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.TeacherDto;
import java.util.List;

public interface TeacherService {
    TeacherDto createTeacher(TeacherDto dto);
    TeacherDto getTeacherById(Long id);
    List<TeacherDto> getAllTeachers();
    TeacherDto updateTeacher(Long id, TeacherDto dto);
    void deleteTeacher(Long id);
}