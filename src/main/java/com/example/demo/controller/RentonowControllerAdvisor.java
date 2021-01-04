package com.example.demo.controller;

import com.example.demo.exception.GuestNotFoundException;
import com.example.demo.exception.HostNotFoundException;
import com.example.demo.exception.PropertyNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@ControllerAdvice
public class RentonowControllerAdvisor {


    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(AccountNotFoundException.class)
    public void handleNotFound(AccountNotFoundException ex) {
        log.error("Requested account not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(GuestNotFoundException.class)
    public void handleBadRequest(GuestNotFoundException ex) {
        log.error("Invalid account supplied in request");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(HostNotFoundException.class)
    public void handleBadRequest(HostNotFoundException ex) {
        log.error("Invalid account supplied in request");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(PropertyNotFoundException.class)
    public void handleBadRequest(PropertyNotFoundException ex) {
        log.error("Invalid account supplied in request");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(ReservationNotFoundException.class)
    public void handleBadRequest(ReservationNotFoundException ex) {
        log.error("Invalid account supplied in request");
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        log.error("An error occurred processing request" + ex);
    }

}
