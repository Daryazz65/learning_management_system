package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toDto(Teacher teacher);
    Teacher toEntity(TeacherDto dto);
}
