package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
