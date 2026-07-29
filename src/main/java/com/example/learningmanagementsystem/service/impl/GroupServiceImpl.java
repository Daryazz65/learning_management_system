package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.Student;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.StudentRepository;
import com.example.learningmanagementsystem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupById(Long id){
        return groupRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Группа не найдена"));
    }

    @Override
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @Override
    public Group updateGroup(Long id, Group group){
        Group existingGroup = getGroupById(id);
        existingGroup.setName(group.getName());
        return groupRepository.save(existingGroup);
    }

    @Override
    public void deleteGroup(Long id){
        groupRepository.deleteById(id);
    }

    @Override
    public Group addStudentToGroup(Long studentId, Long groupId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Студент не найден"));

        Group group = getGroupById(groupId);

        group.getStudents().add(student);
        return groupRepository.save(group);
    }
}
