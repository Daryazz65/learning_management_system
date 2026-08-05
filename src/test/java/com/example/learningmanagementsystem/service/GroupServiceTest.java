package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.GroupDto;
import com.example.learningmanagementsystem.entity.Group;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {

    @Test
    void testGroupDtoCreation() {
        GroupDto dto = new GroupDto(1L, "ИС-21", null);

        assertEquals(1L, dto.id());
        assertEquals("ИС-21", dto.name());
    }

    @Test
    void testGroupEntityCreation() {
        Group group = new Group();
        group.setId(1L);
        group.setName("ИС-21");

        assertEquals(1L, group.getId());
        assertEquals("ИС-21", group.getName());
    }
}