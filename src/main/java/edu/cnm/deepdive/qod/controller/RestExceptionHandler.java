package edu.cnm.deepdive.qod.controller;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({NoSuchElementException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void notFound() {

  }

}
