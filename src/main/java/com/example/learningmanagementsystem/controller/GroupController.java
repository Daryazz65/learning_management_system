package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.GroupDto;
import com.example.learningmanagementsystem.mapper.GroupMapper;
import com.example.learningmanagementsystem.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@Valid @RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.createGroup(groupMapper.toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.getGroupById(id)));
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups().stream().map(groupMapper::toDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.updateGroup(id, groupMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<GroupDto> addStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.addStudentToGroup(studentId, groupId)));
    }
}