package com.assignmentSubmission.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @Column(name = "assignmentid", nullable = false)
    private Long assignmentid;

    @Column(name = "task", nullable = false, length = 500)
    private String task;

    @Column(name = "status")
    private String status;

    @Column(name = "admin_name" ,nullable = false)
    private String adminname;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userid;



}