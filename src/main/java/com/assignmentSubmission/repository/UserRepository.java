package com.assignmentSubmission.repository;

import com.assignmentSubmission.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String>
{
    boolean existsByEmailid(String email);

    Optional<Users> findByUserid(String userid);

    Iterable<Users> findByRole(String role);

}