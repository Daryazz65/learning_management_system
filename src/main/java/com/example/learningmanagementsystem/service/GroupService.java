package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Group;

import java.util.List;

public interface GroupService {
    Group createGroup(Group group);
    Group getGroupById(Long id);
    List<Group> getAllGroups();
    Group updateGroup(Long id, Group group);
    void deleteGroup(Long id);

    Group addStudentToGroup(Long studentId, Long groupId);
}
