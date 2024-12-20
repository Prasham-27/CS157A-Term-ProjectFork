package com.cs157a.studentmanagement.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles the HTTP 500 response in case an exception occurs in one of the
 * endpoints.
 */
@RestControllerAdvice
public class GlobalExceptionController {
   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
              .body("Method not allowed: " + ex.getMethod());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleException(Exception ex) {

      // Ignore
      if (ex instanceof ExpiredJwtException) {
         return null;
      }
      ex.printStackTrace();  // Log the exception
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An unexpected error occurred.");
   }
}
