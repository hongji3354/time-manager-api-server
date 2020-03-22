package kr.healthcare.timemanagerapi.advice;

import kr.healthcare.timemanagerapi.exception.TokenAuthorizationException;
import kr.healthcare.timemanagerapi.exception.TokenEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenValidationAdvice {

    @ExceptionHandler(TokenEmptyException.class)
    public ResponseEntity tokenEmptyException(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenAuthorizationException.class)
    public ResponseEntity tokenAuthorizationException(){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
