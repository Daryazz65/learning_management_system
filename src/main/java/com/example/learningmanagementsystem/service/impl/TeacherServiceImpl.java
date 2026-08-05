package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.exception.TeacherNotFoundException;
import com.example.learningmanagementsystem.mapper.TeacherMapper;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import com.example.learningmanagementsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDto createTeacher(TeacherDto dto) {
        Teacher teacher = teacherMapper.toEntity(dto);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
        return teacherMapper.toDto(teacher);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
        teacher.setName(dto.name());
        teacher.setLastName(dto.lastName());
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(id);
        }
        teacherRepository.deleteById(id);
    }
}