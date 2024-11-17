package com.assignmentSubmission.services;

import com.assignmentSubmission.entity.Assignment;
import com.assignmentSubmission.entity.Users;
import com.assignmentSubmission.payload.AssignmentDto;
import com.assignmentSubmission.repository.AssignmentRepository;
import com.assignmentSubmission.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentServices {
    private AssignmentRepository ar;
    private UserRepository ur;

    public AssignmentServices(AssignmentRepository ar, UserRepository ur) {
        this.ar = ar;
        this.ur = ur;
    }
    // Implement user assignment service methods here
    public AssignmentDto submitAssignment(AssignmentDto details, String userID)
    {
        // Validate user existence in the database and retrieve the user
        Optional<Users> byUserid = ur.findByUserid(userID);
        Users user = byUserid.get();

        // Create a new assignment object and set the user and other details from the provided details
        Assignment assignment = new Assignment();
        assignment.setAssignmentid(details.getAssignmentid());
        assignment.setTask(details.getTask());
        assignment.setAdminname(details.getAdminname());
        assignment.setUserid(user);

        // Save or update the assignment in the database
        Assignment saved = ar.save(assignment);

        // Convert the saved assignment to AssignmentDto and return it
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setAssignmentid(saved.getAssignmentid());
        assignmentDto.setTask(saved.getTask());
        assignmentDto.setAdminname(saved.getAdminname());
        assignmentDto.setUserid(saved.getUserid());
        return assignmentDto;

    }
}
