package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.TeacherDto;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TeacherControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TeacherRepository teacherRepository;

    private RestTemplate restTemplate;
    private String baseUrl;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        baseUrl = "http://localhost:" + port + "/api/v1/teachers";

        teacherRepository.deleteAll();

        var t1 = new com.example.learningmanagementsystem.entity.Teacher();
        t1.setName("Иван");
        t1.setLastName("Иванов");
        teacherRepository.save(t1);

        var t2 = new com.example.learningmanagementsystem.entity.Teacher();
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
        var saved = teacherRepository.findAll().get(0);

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
        var saved = teacherRepository.findAll().get(0);
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
        var saved = teacherRepository.findAll().get(0);

        restTemplate.delete(baseUrl + "/" + saved.getId());

        assertEquals(1, teacherRepository.findAll().size());
        assertFalse(teacherRepository.existsById(saved.getId()));
    }
}