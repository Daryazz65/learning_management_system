package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.entity.Schedule;
import com.example.learningmanagementsystem.exception.ResourceNotFoundException;
import com.example.learningmanagementsystem.repository.ScheduleRepository;
import com.example.learningmanagementsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Расписание", id));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Page<Schedule> getScheduleByGroupId(Long groupId, Pageable pageable) {
        return scheduleRepository.findByGroupId(groupId, pageable);
    }

    @Override
    public Page<Schedule> getScheduleByTeacherId(Long teacherId, Pageable pageable) {
        return scheduleRepository.findByTeacherId(teacherId, pageable);
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule existingSchedule = getScheduleById(id);
        existingSchedule.setGroup(schedule.getGroup());
        existingSchedule.setCourse(schedule.getCourse());
        existingSchedule.setTeacher(schedule.getTeacher());
        existingSchedule.setDateStart(schedule.getDateStart());
        existingSchedule.setDateFinish(schedule.getDateFinish());
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = getScheduleById(id);
        scheduleRepository.delete(schedule);
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteOldSchedules() {
        scheduleRepository.deleteByDateFinishBefore(LocalDateTime.now());
    }
}