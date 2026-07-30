package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TeacherControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TeacherRepository teacherRepository;

    private RestTemplate restTemplate;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        baseUrl = "http://localhost:" + port + "/api/v1/teachers";

        teacherRepository.deleteAll();
        Teacher teacher1 = new Teacher();
        teacher1.setName("Иван");
        teacher1.setLastName("Иванов");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Петр");
        teacher2.setLastName("Петров");
        teacherRepository.save(teacher2);
    }

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    void getAllTeachers_ShouldReturnListOfTeachers() {
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);

        assertEquals(200, response.getStatusCodeValue());
        List<?> teachers = response.getBody();
        assertNotNull(teachers);
        assertEquals(2, teachers.size());
    }

    @Test
    void getTeacherById_ShouldReturnTeacher() {
        Teacher savedTeacher = teacherRepository.findAll().get(0);

        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/" + savedTeacher.getId(), String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(savedTeacher.getName()));
        assertTrue(response.getBody().contains(savedTeacher.getLastName()));
    }

    @Test
    void getTeacherById_WhenNotFound_ShouldReturn404() {
        Long nonExistentId = 999L;

        try {
            restTemplate.getForEntity(baseUrl + "/" + nonExistentId, String.class);
            fail("Ожидалось исключение HttpClientErrorException");
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());
        }
    }

    @Test
    void createTeacher_ShouldCreateAndReturnTeacher() {
        String newTeacherJson = """
                {
                    "name": "Мария",
                    "lastName": "Сидорова"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(newTeacherJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Мария"));
        assertTrue(response.getBody().contains("Сидорова"));

        List<Teacher> teachers = teacherRepository.findAll();
        assertEquals(3, teachers.size());
    }

    @Test
    void updateTeacher_ShouldUpdateAndReturnTeacher() {
        Teacher savedTeacher = teacherRepository.findAll().get(0);

        String updatedTeacherJson = """
                {
                    "name": "Обновленный",
                    "lastName": "Иванов"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(updatedTeacherJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/" + savedTeacher.getId(),
                HttpMethod.PUT,
                entity,
                String.class
        );

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Обновленный"));

        Teacher updated = teacherRepository.findById(savedTeacher.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("Обновленный", updated.getName());
    }

    @Test
    void deleteTeacher_ShouldDeleteTeacher() {
        Teacher savedTeacher = teacherRepository.findAll().get(0);
        Long teacherId = savedTeacher.getId();

        restTemplate.delete(baseUrl + "/" + teacherId);

        List<Teacher> teachers = teacherRepository.findAll();
        assertEquals(1, teachers.size());
        assertFalse(teachers.stream().anyMatch(t -> t.getId().equals(teacherId)));
    }
}