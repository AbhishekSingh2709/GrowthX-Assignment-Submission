package com.assignmentSubmission.controller;

import com.assignmentSubmission.entity.Assignment;
import com.assignmentSubmission.payload.AssignmentDto;
import com.assignmentSubmission.payload.TokenDto;
import com.assignmentSubmission.payload.UserDto;
import com.assignmentSubmission.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Admin")
public class AdminController
{
   private UserServices us;

    public AdminController(UserServices us) {
        this.us = us;
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<?> adminRegister(@Valid @RequestBody UserDto userDetails , BindingResult result)
    {
        // Perform admin registration logic here
        // If any validation errors occur, return an error message with status code
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        ResponseEntity<?> admin = us.addUsers(userDetails);
        // If registration is successful, return a success message with status code
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> userLogin(@RequestBody UserDto loginDetails)
    {
        // Perform user login logic here
        TokenDto verifyUser = us.verifyUsers(loginDetails);
        if (verifyUser!= null)
        {
            return new ResponseEntity<>(verifyUser, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid User ID or password", HttpStatus.OK);
    }

    @GetMapping("/fetchAssignments")
    public ResponseEntity<?> fetchAssignments(@RequestParam String adminName)
    {
        List<Assignment> assignments = us.fetchAssignments(adminName);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PutMapping("/updateStatusAccept")
    public ResponseEntity<?> updateAssignmentStatusAccepted(@RequestParam Long id)
    {
        AssignmentDto assignmentDto = us.updateStatusToAccepted(id);
        if (assignmentDto != null)
        {
            return new ResponseEntity<>(assignmentDto, HttpStatus.OK);
        }
        else return new ResponseEntity<>("No Assignment ID found" , HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateStatusRejected")
    public ResponseEntity<?> updateAssignmentStatusRejected(@RequestParam Long id)
    {
        AssignmentDto assignmentDto = us.updateStatusToRejected(id);
        if (assignmentDto != null)
        {
            return new ResponseEntity<>(assignmentDto, HttpStatus.OK);
        }
        else return new ResponseEntity<>("No Assignment ID found" , HttpStatus.BAD_REQUEST);
    }

}
