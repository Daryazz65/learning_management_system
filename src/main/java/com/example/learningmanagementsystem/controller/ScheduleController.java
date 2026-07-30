package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.dto.ScheduleDto;
import com.example.learningmanagementsystem.mapper.ScheduleMapper;
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
public class ScheduleController{
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@Valid @RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleMapper.toDto(scheduleService.createSchedule(scheduleMapper.toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleMapper.toDto(scheduleService.getScheduleById(id)));
    }


    @GetMapping("/group/{groupId}")
    public ResponseEntity<Page<ScheduleDto>> getScheduleByGroup(
            @PathVariable Long groupId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ScheduleDto> dtoPage = scheduleService.getScheduleByGroupId(groupId, pageable)
                .map(scheduleMapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Page<ScheduleDto>> getScheduleByTeacher(
            @PathVariable Long teacherId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ScheduleDto> dtoPage = scheduleService.getScheduleByTeacherId(teacherId, pageable)
                .map(scheduleMapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleMapper.toDto(scheduleService.updateSchedule(id, scheduleMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}