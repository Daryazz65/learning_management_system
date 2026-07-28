package com.example.learningmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name= "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany
    @JoinTable(
            name="students_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name="students_id")
    )
    private List<Student> students;
}
