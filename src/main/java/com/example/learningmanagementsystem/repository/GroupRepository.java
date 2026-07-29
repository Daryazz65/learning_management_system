package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT g FROM Group g JOIN FETCH g.students WHERE g.id = :id")
    Optional<Group> findByIdWithStudents(@Param("id") Long id);
}
