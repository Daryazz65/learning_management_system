package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.StudentDto;
import com.example.learningmanagementsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groups", ignore = true)
    Student toEntity(StudentDto dto);

    @Mapping(target = "groupIds", ignore = true)
    StudentDto toDto(Student student);

    default List<Long> mapGroupsToIds(List<Student> students) {
        if (students == null) {
            return null;
        }
        return students.stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }
}