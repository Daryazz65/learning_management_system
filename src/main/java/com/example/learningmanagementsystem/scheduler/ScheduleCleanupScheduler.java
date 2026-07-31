package com.example.learningmanagementsystem.scheduler;

import com.example.learningmanagementsystem.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleCleanupScheduler {

    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron = "${app.schedule.cleanup.cron}")
    @Transactional
    public void deleteOldSchedules() {
        log.info("Запуск очистки старых расписаний");
        scheduleRepository.deleteByDateFinishBefore(LocalDateTime.now());
        log.info("Очистка старых расписаний завершена");
    }
}