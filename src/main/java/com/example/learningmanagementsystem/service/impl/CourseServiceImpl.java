package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.dto.CourseDto;
import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.exception.CourseNotFoundException;
import com.example.learningmanagementsystem.exception.TeacherNotFoundException;
import com.example.learningmanagementsystem.mapper.CourseMapper;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import com.example.learningmanagementsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto createCourse(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        Teacher teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new TeacherNotFoundException(dto.teacherId()));
        course.setTeacher(teacher);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        Teacher teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new TeacherNotFoundException(dto.teacherId()));
        course.setName(dto.name());
        course.setDescription(dto.description());
        course.setTeacher(teacher);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}