package com.stackroute.muzixapplication.controller;

import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value= TrackAlreadyExistsException.class)
    public ResponseEntity<String> exception(TrackAlreadyExistsException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value= TrackNotFoundException.class)
    public ResponseEntity<String> exception1(TrackNotFoundException exception1)
    {
        return new ResponseEntity<String>(exception1.getMessage(), HttpStatus.CONFLICT);
    }

}
