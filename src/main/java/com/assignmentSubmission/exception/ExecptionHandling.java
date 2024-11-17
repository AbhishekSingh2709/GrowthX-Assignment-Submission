package com.assignmentSubmission.exception;

import com.assignmentSubmission.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class ExecptionHandling
{
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFound ex , WebRequest webRequest)
    {
        ErrorDetails error = new ErrorDetails
                (ex.getMessage(), new Date() ,webRequest.getDescription(true));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(Exception ex , WebRequest webRequest)
    {
        ErrorDetails error = new ErrorDetails
                (ex.getMessage(), new Date() , webRequest.getDescription(true));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
