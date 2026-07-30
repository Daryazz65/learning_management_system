package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.mapper.TeacherMapper;
import com.example.learningmanagementsystem.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(teacherMapper.toDto(teacherService.createTeacher(teacherMapper.toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherMapper.toDto(teacherService.getTeacherById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> dtos = teacherService.getAllTeachers().stream()
                .map(teacherMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(teacherMapper.toDto(teacherService.updateTeacher(id, teacherMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}