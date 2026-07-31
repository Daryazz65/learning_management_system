package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.GroupDto;
import java.util.List;

public interface GroupService {
    GroupDto createGroup(GroupDto dto);
    GroupDto getGroupById(Long id);
    List<GroupDto> getAllGroups();
    GroupDto updateGroup(Long id, GroupDto dto);
    void deleteGroup(Long id);
    GroupDto addStudentToGroup(Long studentId, Long groupId);
}