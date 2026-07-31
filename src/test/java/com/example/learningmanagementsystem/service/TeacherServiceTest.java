package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.exception.TeacherNotFoundException;
import com.example.learningmanagementsystem.mapper.TeacherMapper;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import com.example.learningmanagementsystem.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    void getTeacherById_Success() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Иван");
        teacher.setLastName("Иванов");

        TeacherDto dto = new TeacherDto(1L, "Иван", "Иванов");

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(dto);

        TeacherDto result = teacherService.getTeacherById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Иван", result.name());
    }

    @Test
    void getTeacherById_NotFound() {
        when(teacherRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacherById(99L));
    }

    @Test
    void getAllTeachers_Success() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        TeacherDto dto = new TeacherDto(1L, "Иван", "Иванов");

        when(teacherRepository.findAll()).thenReturn(List.of(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(dto);

        List<TeacherDto> result = teacherService.getAllTeachers();

        assertEquals(1, result.size());
        assertEquals("Иван", result.get(0).name());
    }
}