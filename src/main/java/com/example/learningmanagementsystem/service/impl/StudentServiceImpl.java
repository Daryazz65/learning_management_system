package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.dto.StudentDto;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.Student;
import com.example.learningmanagementsystem.exception.StudentNotFoundException;
import com.example.learningmanagementsystem.mapper.StudentMapper;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.StudentRepository;
import com.example.learningmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto createStudent(StudentDto dto) {
        Student student = studentMapper.toEntity(dto);
        if (dto.groupIds() != null) {
            student.setGroups(new HashSet<>(groupRepository.findAllById(dto.groupIds())));
        }
        return toDtoWithGroups(studentRepository.save(student));
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toDtoWithGroups(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toDtoWithGroups)
                .toList();
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(dto.name());
        student.setLastName(dto.lastName());
        if (dto.groupIds() != null) {
            student.setGroups(new HashSet<>(groupRepository.findAllById(dto.groupIds())));
        }
        return toDtoWithGroups(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDto toDtoWithGroups(Student student) {
        List<Long> groupIds = student.getGroups().stream()
                .map(Group::getId)
                .toList();
        return new StudentDto(student.getId(), student.getName(), student.getLastName(), groupIds);
    }
}