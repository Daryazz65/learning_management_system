package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findByGroupId(Long groupId, Pageable pageable);

    Page<Schedule> findByTeacherId(Long teacherId, Pageable pageable);

    void deleteByDateFinishBefore(LocalDateTime date);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE " +
            "(s.teacher.id = :teacherId OR s.group.id = :groupId) " +
            "AND s.dateStart < :dateFinish AND s.dateFinish > :dateStart " +
            "AND (:excludeId IS NULL OR s.id <> :excludeId)")
    long countConflicts(@Param("teacherId") Long teacherId,
                        @Param("groupId") Long groupId,
                        @Param("dateStart") LocalDateTime dateStart,
                        @Param("dateFinish") LocalDateTime dateFinish,
                        @Param("excludeId") Long excludeId);
}