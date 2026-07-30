package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.CourseDto;
import com.example.learningmanagementsystem.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "teacher", ignore = true)
    Course toEntity(CourseDto dto);
    @Mapping(target = "teacherId", source = "teacher.id")
    CourseDto toDto(Course course);
}
