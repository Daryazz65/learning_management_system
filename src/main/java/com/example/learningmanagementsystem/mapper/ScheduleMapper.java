package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.ScheduleDto;
import com.example.learningmanagementsystem.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Schedule toEntity(ScheduleDto dto);

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    ScheduleDto toDto(Schedule schedule);
}