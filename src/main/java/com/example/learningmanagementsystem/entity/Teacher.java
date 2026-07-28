package com.example.learningmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 100)
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}
