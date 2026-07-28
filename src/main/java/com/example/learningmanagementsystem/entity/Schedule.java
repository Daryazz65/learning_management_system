package com.example.learningmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "group_id", nullable = false)
    private Group group;

    @Column(name="date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name="date_finish", nullable = false)
    private LocalDate dateFinish;
}