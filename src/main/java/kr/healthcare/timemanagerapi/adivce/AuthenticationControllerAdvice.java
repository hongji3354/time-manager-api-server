package kr.healthcare.timemanagerapi.adivce;

import kr.healthcare.timemanagerapi.exception.AuthenticationEmptyException;
import kr.healthcare.timemanagerapi.exception.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(AuthenticationEmptyException.class)
    @ResponseBody
    public Map<String,String> AuthenticationEmptyException(){
        //TODO 2020.02.14 : return message 구현하기
        return null;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Map<String,String> AuthenticationException(){
        //TODO 2020.02.14 : return message 구현하기
        return null;
    }
}
