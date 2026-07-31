package com.example.learningmanagementsystem.service.impl;

import com.example.learningmanagementsystem.dto.ScheduleDto;
import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.Schedule;
import com.example.learningmanagementsystem.entity.Teacher;
import com.example.learningmanagementsystem.exception.*;
import com.example.learningmanagementsystem.mapper.ScheduleMapper;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.ScheduleRepository;
import com.example.learningmanagementsystem.repository.TeacherRepository;
import com.example.learningmanagementsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    @Transactional
    public ScheduleDto createSchedule(ScheduleDto dto) {
        validateScheduleConflict(null, dto.teacherId(), dto.groupId(), dto.dateStart(), dto.dateFinish());

        Schedule schedule = scheduleMapper.toEntity(dto);
        schedule.setGroup(groupRepository.findById(dto.groupId())
                .orElseThrow(() -> new GroupNotFoundException(dto.groupId())));
        schedule.setCourse(courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new CourseNotFoundException(dto.courseId())));
        schedule.setTeacher(teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new TeacherNotFoundException(dto.teacherId())));

        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public Page<ScheduleDto> getScheduleByGroupId(Long groupId, Pageable pageable) {
        return scheduleRepository.findByGroupId(groupId, pageable)
                .map(scheduleMapper::toDto);
    }

    @Override
    public Page<ScheduleDto> getScheduleByTeacherId(Long teacherId, Pageable pageable) {
        return scheduleRepository.findByTeacherId(teacherId, pageable)
                .map(scheduleMapper::toDto);
    }

    @Override
    @Transactional
    public ScheduleDto updateSchedule(Long id, ScheduleDto dto) {
        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        validateScheduleConflict(id, dto.teacherId(), dto.groupId(), dto.dateStart(), dto.dateFinish());

        existing.setGroup(groupRepository.findById(dto.groupId())
                .orElseThrow(() -> new GroupNotFoundException(dto.groupId())));
        existing.setCourse(courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new CourseNotFoundException(dto.courseId())));
        existing.setTeacher(teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new TeacherNotFoundException(dto.teacherId())));
        existing.setDateStart(dto.dateStart());
        existing.setDateFinish(dto.dateFinish());

        return scheduleMapper.toDto(scheduleRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }
        scheduleRepository.deleteById(id);
    }

    private void validateScheduleConflict(Long excludeId, Long teacherId, Long groupId,
                                          java.time.LocalDateTime start, java.time.LocalDateTime finish) {
        long conflicts = scheduleRepository.countConflicts(teacherId, groupId, start, finish, excludeId);
        if (conflicts > 0) {
            throw new ScheduleConflictException(
                    "Расписание пересекается с существующим занятием у преподавателя или группы");
        }
    }
}