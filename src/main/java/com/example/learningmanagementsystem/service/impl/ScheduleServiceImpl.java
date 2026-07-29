package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.entity.Schedule;
import com.example.learningmanagementsystem.repository.ScheduleRepository;
import com.example.learningmanagementsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
    public Schedule getScheduleById(Long id){
        return scheduleRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Расписание не найдено"));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule existingSchedule = getScheduleById(id);
        existingSchedule.setDateStart(schedule.getDateStart());
        existingSchedule.setDateFinish(schedule.getDateFinish());
        existingSchedule.setGroup(schedule.getGroup());
        existingSchedule.setCourse(schedule.getCourse());
        existingSchedule.setTeacher(schedule.getTeacher());
        return scheduleRepository.save(existingSchedule);
    }
    @Override
    public void deleteSchedule(Long id){
        scheduleRepository.deleteById(id);
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
    public void deleteOldSchedules(LocalDateTime date){
        scheduleRepository.deleteByDateFinishBefore(date);
    }
}