package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.ScheduleDto;
import com.example.learningmanagementsystem.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@Valid @RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleService.createSchedule(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Page<ScheduleDto>> getScheduleByGroup(
            @PathVariable Long groupId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(scheduleService.getScheduleByGroupId(groupId, pageable));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Page<ScheduleDto>> getScheduleByTeacher(
            @PathVariable Long teacherId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(scheduleService.getScheduleByTeacherId(teacherId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}