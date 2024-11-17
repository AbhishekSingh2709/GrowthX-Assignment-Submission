package com.assignmentSubmission.repository;

import com.assignmentSubmission.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long>
{
    Iterable<Assignment> findByAdminname(String adminname);
}