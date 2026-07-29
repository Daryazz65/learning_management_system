package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository <Student, Long> {

    @Query("SELECT s FROM Student s JOIN FETCH s.groups WHERE s.id = :id")
    Optional<Student> findByIdWithGroups(@Param("id") Long id);
}
