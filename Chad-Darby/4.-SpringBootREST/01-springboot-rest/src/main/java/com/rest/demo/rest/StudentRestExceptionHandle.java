package com.rest.demo.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandle {
    /* Add an exception handler using @ExceptionHandler 
    Type of the response body (StudentErrorResponse) 
    Exception type to handle / catch (StudentNotFoundException) */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity -> error (body), status code (NOT FOUND)
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /* Add another exception handler to catch any exception (catch all) 
    Exception type to handle / catch (Exception) handle generic exceptions */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
