package com.assignmentSubmission.exception;

public class ResourceNotFound extends RuntimeException
{
    private String message;
    public ResourceNotFound(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return this.message;
    }
}
