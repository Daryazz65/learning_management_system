package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.dto.GroupDto;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.Student;
import com.example.learningmanagementsystem.exception.GroupNotFoundException;
import com.example.learningmanagementsystem.exception.StudentNotFoundException;
import com.example.learningmanagementsystem.mapper.GroupMapper;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.StudentRepository;
import com.example.learningmanagementsystem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    @Override
    public GroupDto createGroup(GroupDto dto) {
        Group group = groupMapper.toEntity(dto);
        if (dto.studentIds() != null) {
            group.setStudents(new HashSet<>(studentRepository.findAllById(dto.studentIds())));
        }
        return toDtoWithStudents(groupRepository.save(group));
    }

    @Override
    public GroupDto getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        return toDtoWithStudents(group);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::toDtoWithStudents)
                .toList();
    }

    @Override
    public GroupDto updateGroup(Long id, GroupDto dto) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        group.setName(dto.name());
        if (dto.studentIds() != null) {
            group.setStudents(new HashSet<>(studentRepository.findAllById(dto.studentIds())));
        }
        return toDtoWithStudents(groupRepository.save(group));
    }

    @Override
    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException(id);
        }
        groupRepository.deleteById(id);
    }

    @Override
    public GroupDto addStudentToGroup(Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        group.getStudents().add(student);
        return toDtoWithStudents(groupRepository.save(group));
    }

    private GroupDto toDtoWithStudents(Group group) {
        List<Long> studentIds = group.getStudents().stream()
                .map(Student::getId)
                .toList();
        return new GroupDto(group.getId(), group.getName(), studentIds);
    }
}