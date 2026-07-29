package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findByGroupId(Long groupId, Pageable pageable);
    Page<Schedule> findByTeacherId(Long teacherId,Pageable pageable);

    int deleteByDateFinishBefore(LocalDateTime date);
}