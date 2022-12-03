package org.example.controller;

import org.example.exception.DoctorTimetableException;
import org.example.exception.OtherException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationController {

    @ExceptionHandler(IllegalArgumentException.class)
    public IllegalArgumentException exceptionHandler(IllegalArgumentException e) {
        return e;
    }

    @ExceptionHandler(DoctorTimetableException.class)
    public DoctorTimetableException exceptionHandler(DoctorTimetableException e) {
        return e;
    }

    @ExceptionHandler(OtherException.class)
    public OtherException exceptionHandler(OtherException e) {
        return e;
    }
}
