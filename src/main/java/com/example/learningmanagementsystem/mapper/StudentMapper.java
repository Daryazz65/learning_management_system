package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.StudentDto;
import com.example.learningmanagementsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groups", ignore = true)
    Student toEntity(StudentDto dto);

    @Mapping(target = "groupIds", ignore = true)
    StudentDto toDto(Student student);
}