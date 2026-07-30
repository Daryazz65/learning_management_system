package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.GroupDto;
import com.example.learningmanagementsystem.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", ignore = true)
    Group toEntity(GroupDto dto);

    @Mapping(target = "studentIds", source = "students")
    GroupDto toDto(Group group);
}