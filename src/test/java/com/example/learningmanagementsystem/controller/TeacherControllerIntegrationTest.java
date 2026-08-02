package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.integration.AbstractIT;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherControllerIntegrationTest extends AbstractIT {

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
        initRestTemplate("/api/v1/teachers");

        teacherRepository.deleteAll();

        Teacher t1 = new Teacher();
        t1.setName("Иван");
        t1.setLastName("Иванов");
        teacherRepository.save(t1);

        Teacher t2 = new Teacher();
        t2.setName("Петр");
        t2.setLastName("Петров");
        teacherRepository.save(t2);
    }

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    void getAllTeachers_ShouldReturnListOfTeachers() {
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getTeacherById_ShouldReturnTeacher() {
        Teacher saved = teacherRepository.findAll().get(0);

        ResponseEntity<String> response = restTemplate.getForEntity(
                baseUrl + "/" + saved.getId(), String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(saved.getName()));
    }

    @Test
    void getTeacherById_WhenNotFound_ShouldReturn404() {
        try {
            restTemplate.getForEntity(baseUrl + "/999", String.class);
            fail("Ожидалось исключение");
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());
        }
    }

    @Test
    void createTeacher_ShouldCreateAndReturnTeacher() throws Exception {
        String json = objectMapper.writeValueAsString(new TeacherDto(null, "Мария", "Сидорова"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Мария"));
        assertEquals(3, teacherRepository.findAll().size());
    }

    @Test
    void updateTeacher_ShouldUpdateAndReturnTeacher() throws Exception {
        Teacher saved = teacherRepository.findAll().get(0);
        String json = objectMapper.writeValueAsString(new TeacherDto(null, "Обновленный", "Иванов"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/" + saved.getId(),
                HttpMethod.PUT,
                entity,
                String.class
        );

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Обновленный"));
    }

    @Test
    void deleteTeacher_ShouldDeleteTeacher() {
        Teacher saved = teacherRepository.findAll().get(0);

        restTemplate.delete(baseUrl + "/" + saved.getId());

        assertEquals(1, teacherRepository.findAll().size());
        assertFalse(teacherRepository.existsById(saved.getId()));
    }
}