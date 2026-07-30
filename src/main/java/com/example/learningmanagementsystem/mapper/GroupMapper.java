package com.example.learningmanagementsystem.mapper;

import com.example.learningmanagementsystem.dto.GroupDto;
import com.example.learningmanagementsystem.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", ignore = true)
    Group toEntity(GroupDto dto);

    @Mapping(target = "studentIds", ignore = true)
    GroupDto toDto(Group group);

    default List<Long> mapStudentsToIds(List<Group> groups) {
        if (groups == null) {
            return null;
        }
        return groups.stream()
                .map(Group::getId)
                .collect(Collectors.toList());
    }
}