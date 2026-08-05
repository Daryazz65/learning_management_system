package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.dto.ScheduleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    ScheduleDto createSchedule(ScheduleDto dto);
    ScheduleDto getScheduleById(Long id);
    Page<ScheduleDto> getScheduleByGroupId(Long groupId, Pageable pageable);
    Page<ScheduleDto> getScheduleByTeacherId(Long teacherId, Pageable pageable);
    ScheduleDto updateSchedule(Long id, ScheduleDto dto);
    void deleteSchedule(Long id);
}