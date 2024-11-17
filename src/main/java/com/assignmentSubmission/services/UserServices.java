package com.assignmentSubmission.services;

import com.assignmentSubmission.entity.Assignment;
import com.assignmentSubmission.entity.Users;
import com.assignmentSubmission.payload.AssignmentDto;
import com.assignmentSubmission.payload.TokenDto;
import com.assignmentSubmission.payload.UserDto;
import com.assignmentSubmission.repository.AssignmentRepository;
import com.assignmentSubmission.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices
{
    private UserRepository ur;
    private JwtService js;
    private AssignmentRepository ar;

    public UserServices(UserRepository ur, JwtService js, AssignmentRepository ar) {
        this.ur = ur;
        this.js = js;
        this.ar = ar;
    }

    // Convert UserDto to UserEntity
    public Users UserDtoToEntity(UserDto userDetails)
    {
        Users users = new Users();
        users.setUserid(userDetails.getUserid());
        users.setEmailid(userDetails.getEmailid());
        users.setPassword(BCrypt.hashpw(userDetails.getPassword(),BCrypt.gensalt(10)));
        users.setRole(userDetails.getRole());
        return users;
    }

    // Convert UserEntity to UserDto
    public UserDto UserEntityToDto(Users userDetails)
    {
        UserDto userDto = new UserDto();
        userDto.setUserid(userDetails.getUserid());
        userDto.setPassword(userDetails.getPassword());
        userDto.setEmailid(userDetails.getEmailid());
        userDto.setRole(userDetails.getRole());
        return userDto;
    }

    // Registering new users in system
    public ResponseEntity<?> addUsers(UserDto userDetails)
    {
        // Validate user details (e.g., email uniqueness, password complexity)
        if (ur.existsByEmailid(userDetails.getEmailid()))
        {
            return new ResponseEntity<String>("Email already exists" , HttpStatus.OK);
        }

        // Convert UserDto to UserEntity
        Users users = UserDtoToEntity(userDetails);

        // Add user to the database
        Users saved = ur.save(users);

        // Convert UserEntity to UserDto
        UserDto userInfo = UserEntityToDto(saved);

        // Return success message with status code 201 (Created)
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

    public TokenDto verifyUsers(UserDto userDetails)
    {
        Optional<Users> userEntity = ur.findByUserid(userDetails.getUserid());
        if (userEntity.isPresent())
        {
            Users ue = userEntity.get();
            if (BCrypt.checkpw(userDetails.getPassword(), ue.getPassword()))
            {
                String token = js.generateToken(ue);
                TokenDto tokenDto = new TokenDto();
                tokenDto.setType("JWT");
                tokenDto.setToken(token);
                return tokenDto;
            }
        }
        return null;
    }

    public List<Assignment> fetchAssignments (String adminName)
    {

        Iterable<Assignment> assignments = ar.findByAdminname(adminName);
        return (List<Assignment>) assignments;
    }

    public AssignmentDto updateStatusToAccepted(Long id)
    {
        Optional<Assignment> assignment = ar.findById(id);
        if (assignment.isPresent())
        {
            Assignment assignmentEntity = assignment.get();
            assignmentEntity.setStatus("ACCEPTED");
            Assignment saved = ar.save(assignmentEntity);
            AssignmentDto assignmentDto = new AssignmentDto();
            assignmentDto.setAssignmentid(saved.getAssignmentid());
            assignmentDto.setTask(saved.getTask());
            assignmentDto.setStatus(saved.getStatus());
            assignmentDto.setAdminname(saved.getAdminname());
            assignmentDto.setUserid(saved.getUserid());
            return assignmentDto;
        }
        return null;
    }

    public AssignmentDto updateStatusToRejected(Long id)
    {
        Optional<Assignment> assignment = ar.findById(id);
        if (assignment.isPresent())
        {
            Assignment assignmentEntity = assignment.get();
            assignmentEntity.setStatus("REJECTED");
            Assignment saved = ar.save(assignmentEntity);
            AssignmentDto assignmentDto = new AssignmentDto();
            assignmentDto.setAssignmentid(saved.getAssignmentid());
            assignmentDto.setTask(saved.getTask());
            assignmentDto.setStatus(saved.getStatus());
            assignmentDto.setAdminname(saved.getAdminname());
            assignmentDto.setUserid(saved.getUserid());
            return assignmentDto;
        }
        return null;
    }

    public ResponseEntity<?> fetchAdmin()
    {
       Iterable<Users> admins = ur.findByRole("ROLE_ADMIN");
       if (admins!=null) {
           return new ResponseEntity<>(admins, HttpStatus.OK);
       }
       else return new ResponseEntity<>("No admin found", HttpStatus.BAD_REQUEST);
    }
}

