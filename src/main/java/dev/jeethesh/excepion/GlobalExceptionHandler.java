package dev.jeethesh.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(Exception.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public ResponseEntity<String> handleException(Exception e) {
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
 }

 @ExceptionHandler(IllegalArgumentException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + e.getMessage());
 }
}
