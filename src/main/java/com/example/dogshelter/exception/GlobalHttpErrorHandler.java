package com.example.dogshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<Object> handleDogNotFoundException(DogNotFoundException exception) {
        return new ResponseEntity<>("Dog with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<Object> handleVolunteerNotFoundException(VolunteerNotFoundException exception) {
        return new ResponseEntity<>("Volunteer doesn't exist in database", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalkNotFoundException.class)
    public ResponseEntity<Object> handleWalkNotFoundException(WalkNotFoundException exception) {
        return new ResponseEntity<>("Walk with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Object> handleImageNotFoundException(ImageNotFoundException exception) {
        return new ResponseEntity<>("Image doesn't exist", HttpStatus.BAD_REQUEST);
    }

}
