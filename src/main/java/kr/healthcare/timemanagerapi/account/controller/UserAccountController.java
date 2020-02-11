package kr.healthcare.timemanagerapi.account.controller;

import kr.healthcare.timemanagerapi.account.service.UserAccountService;
import kr.healthcare.timemanagerapi.account.vo.UserAccountVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserAccountController {

    @NonNull
    private UserAccountService userAccountService;

    @GetMapping("/api/accounts/{studentNumber}")
    public ResponseEntity studentNumberOverLabCheck(UserAccountVO searchVO){

        Map<String,Boolean> result = new HashMap<>();

        if(userAccountService.studentNumberOverLabCheck(searchVO.getStudentNumber())){
            result.put("RESULT",Boolean.TRUE);
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @GetMapping("/api/account/{memberId}")
    public ResponseEntity memberIdOverLabCheck(UserAccountVO searchVO){

        Map<String,Boolean> result = new HashMap<>();

        if(userAccountService.memberIdOverLabCheck(searchVO.getMemberId())){
            result.put("RESULT",Boolean.TRUE);
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity(result, HttpStatus.OK);
        }

    }

    @PostMapping("/api/account")
    public ResponseEntity register(@RequestBody UserAccountVO insertVO){

        Map<String,Boolean> result = new HashMap<>();

        if(userAccountService.accountRegister(insertVO)){
            result.put("RESULT",Boolean.TRUE);
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity(result, HttpStatus.OK);
        }

    }

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody UserAccountVO searchVO){

        Map result = new HashMap<>();
        String loginResult = userAccountService.login(searchVO);

        if(!loginResult.equals("")){
            result.put("RESULT",Boolean.TRUE);
            result.put("token",loginResult);
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity(result, HttpStatus.OK);
        }


    }

}
