package com.stackroute.muzixapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= TrackAlreadyExistsException.class)
    public ResponseEntity<String> trackAlreadyExistsException(TrackAlreadyExistsException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= TrackNotFoundException.class)
    public ResponseEntity<String> whenTrackNotFoundException(TrackNotFoundException exception1)
    {
        return new ResponseEntity<String>(exception1.getMessage(), HttpStatus.NOT_FOUND);
    }

}
