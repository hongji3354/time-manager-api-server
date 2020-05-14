package kr.healthcare.timemanagerapi.advice;

import io.jsonwebtoken.MalformedJwtException;
import kr.healthcare.timemanagerapi.constant.ResponseFailMessage;
import kr.healthcare.timemanagerapi.exception.TokenEmptyException;
import kr.healthcare.timemanagerapi.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TokenEmptyException.class)
    public ResponseEntity tokenEmptyException(){
        Map<String, String> message = new HashMap<>();
        message.put("resultCode", ResponseFailMessage.FAIL.toString());
        message.put("resultMessage",ResponseFailMessage.H000005.getMessage());
        return new ResponseEntity<Map<String,String>>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity wrongTokenException(){
        Map<String, String> message = new HashMap<>();
        message.put("resultCode", ResponseFailMessage.FAIL.toString());
        message.put("resultMessage",ResponseFailMessage.H000006.getMessage());
        return new ResponseEntity<Map<String,String>>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity unAuthorizedException(){
        Map<String, String> message = new HashMap<>();
        message.put("resultCode", ResponseFailMessage.FAIL.toString());
        message.put("resultMessage",ResponseFailMessage.H000006.getMessage());
        return new ResponseEntity<Map<String,String>>(message, HttpStatus.UNAUTHORIZED);
    }

}
