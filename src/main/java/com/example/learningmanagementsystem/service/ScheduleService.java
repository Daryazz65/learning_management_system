package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    Schedule updateSchedule(Long id, Schedule schedule);
    void deleteSchedule(Long id);

    // Специфичные методы
    Page<Schedule> getScheduleByGroupId(Long groupId, Pageable pageable);
    Page<Schedule> getScheduleByTeacherId(Long teacherId, Pageable pageable);
    void deleteOldSchedules(LocalDateTime date);
}