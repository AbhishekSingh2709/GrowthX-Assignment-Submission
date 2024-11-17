package com.assignmentSubmission.controller;

import com.assignmentSubmission.payload.AssignmentDto;
import com.assignmentSubmission.payload.TokenDto;
import com.assignmentSubmission.payload.UserDto;
import com.assignmentSubmission.services.AssignmentServices;
import com.assignmentSubmission.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users-registration")
public class UsersController
{
    private UserServices us;
    private AssignmentServices ua;

    public UsersController(UserServices us, AssignmentServices ua) {
        this.us = us;
        this.ua = ua;
    }

    // Define the endpoint for user registration
    @PostMapping("/userInfo")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDetails , BindingResult result)
    {
        // Perform user registration logic here
        // If any validation errors occur, return an error message with status code 406 (Not Acceptable)
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        ResponseEntity<?> users = us.addUsers(userDetails);
        // If registration is successful, return a success message with status code 201 (Created)
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @PostMapping("/userLogin")
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

    @PostMapping("/submittingAssignment")
    public ResponseEntity<?> submitAssignment(@RequestBody AssignmentDto assignmentDetails, @RequestParam String userid)
    {
        if (assignmentDetails!=null) {
            // Perform assignment submission logic here
            AssignmentDto assignmentDto = ua.submitAssignment(assignmentDetails , userid);
            return new ResponseEntity<>(assignmentDto, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("Assignment not submitted",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/fetchAdmins")
    public ResponseEntity<?> fetchAdmins()
    {
        // Perform admin fetching logic here
        ResponseEntity<?> admins = us.fetchAdmin();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

}
