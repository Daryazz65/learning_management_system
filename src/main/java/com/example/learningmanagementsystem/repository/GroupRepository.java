package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
