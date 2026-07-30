package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.CourseDto;
import com.example.learningmanagementsystem.mapper.CourseMapper;
import com.example.learningmanagementsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto dto) {
        return ResponseEntity.ok(courseMapper.toDto(courseService.createCourse(courseMapper.toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseMapper.toDto(courseService.getCourseById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses().stream().map(courseMapper::toDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDto dto) {
        return ResponseEntity.ok(courseMapper.toDto(courseService.updateCourse(id, courseMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}