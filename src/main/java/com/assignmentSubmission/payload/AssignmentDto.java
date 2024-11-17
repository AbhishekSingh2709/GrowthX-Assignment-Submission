package com.assignmentSubmission.payload;

import com.assignmentSubmission.entity.Users;
import lombok.Data;

@Data
public class AssignmentDto
{
    private Long assignmentid;
    private String task;
    private String status;
    private String adminname;
    private Users userid;
}
