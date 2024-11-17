package com.assignmentSubmission.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users-registration")
public class Users {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userid;

    @Column(name = "email_id", nullable = false, unique = true,length = 500)
    private String emailid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;
}