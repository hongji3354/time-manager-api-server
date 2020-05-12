package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.constant.ResponseFailMessage;
import kr.healthcare.timemanagerapi.dto.member.MemberDTO;
import kr.healthcare.timemanagerapi.service.member.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @NonNull
    private MemberService memberService;

    @GetMapping("/api/accounts/student-number")
    public ResponseEntity studentNumberOverlapCheck(@Valid MemberDTO.StudentNumberRequest studentNumberRequest,
                                                                                     BindingResult bindingResult){
        MemberDTO.StudentNumberResponse response = new MemberDTO.StudentNumberResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode("FAIL");
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }

        if(memberService.memberSignPossibleWhether(Integer.parseInt(studentNumberRequest.getStudentNumber()))){
            response.setResultCode("SUCCESS");
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }else{
            response.setResultCode("FAIL");
            response.setResultMessage(ResponseFailMessage.H000001.getMessage());
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }
    }
}
