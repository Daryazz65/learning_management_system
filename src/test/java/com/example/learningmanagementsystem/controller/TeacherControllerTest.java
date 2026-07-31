package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeacherService teacherService;

    @Test
    void getTeacherById_Success() throws Exception {
        TeacherDto dto = new TeacherDto(1L, "Иван", "Иванов");
        when(teacherService.getTeacherById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Иван"))
                .andExpect(jsonPath("$.lastName").value("Иванов"));
    }
}