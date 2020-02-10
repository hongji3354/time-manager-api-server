package kr.healthcare.timemanagerapi.controller.user.account;

import kr.healthcare.timemanagerapi.service.user.account.UserAccountService;
import kr.healthcare.timemanagerapi.domain.user.account.UserAccountVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    @NonNull
    private UserAccountService userAccountService;

    //학번중복체크
    @GetMapping("api/v1/user/student-number/{studentNumber}")
    public ResponseEntity studentNumberDuplicateCheck(@Valid UserAccountVO userAccountVO,
                                                      BindingResult bindingResult){

        Map<String,Boolean> result = new HashMap<>();

        if(bindingResult.hasErrors()){
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        if(userAccountService.findByStudentNumberDuplicateCheck(userAccountVO.getStudentNumber()) == 0){
            result.put("RESULT",Boolean.TRUE);
        }else{
            result.put("RESULT",Boolean.FALSE);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //아이디중복체크
    @GetMapping("api/v1/user/student-id/{memberId}")
    public ResponseEntity studentIdDuplicateCheck(@Valid UserAccountVO userAccountVO,
                                                        BindingResult bindingResult){

        Map<String,Boolean> result = new HashMap<>();

        if(bindingResult.hasErrors()){
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        if(userAccountService.findByMemberIdDuplicateCheck(userAccountVO.getMemberId()) == 0){
            result.put("RESULT",Boolean.TRUE);
        }else{
            result.put("RESULT",Boolean.FALSE);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    //회원가입
    @PostMapping("api/v1//user/student/account")
    public ResponseEntity studentRegister(@Valid @RequestBody UserAccountVO userAccountVO,
                                          BindingResult bindingResult){

        Map<String,Boolean> result = new HashMap<>();

        if(bindingResult.hasErrors()){
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        if((userAccountService.findByStudentNumberDuplicateCheck(userAccountVO.getStudentNumber()) != 0) && (userAccountService.findByMemberIdDuplicateCheck(userAccountVO.getMemberId()) != 0)){
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }

        if(userAccountService.persistByStudentAccount(userAccountVO) == 1) {
            result.put("RESULT", Boolean.TRUE);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            result.put("RESULT",Boolean.FALSE);
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
