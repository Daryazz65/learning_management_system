package com.example.learningmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", nullable = false, length = 100)
    private String name;

    @Column(name="last_name", nullable = false, length = 100)
    private String lastName;

    @ManyToMany(mappedBy = "students")
    private List<Group> groups;
}
