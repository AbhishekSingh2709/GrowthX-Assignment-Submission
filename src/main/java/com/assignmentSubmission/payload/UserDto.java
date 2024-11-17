package com.assignmentSubmission.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    // Fields
    @Size(min = 3 , message = "name should be at least 3 characters")
    private String userid;


    private String emailid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;
}
