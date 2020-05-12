package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.constant.ResponseFailMessage;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import kr.healthcare.timemanagerapi.dto.member.MemberDTO;
import kr.healthcare.timemanagerapi.service.member.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @NonNull
    private MemberService memberService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

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

    @GetMapping("/api/accounts/student-email")
    public ResponseEntity studentEmaliOverlapCheck(@Valid MemberDTO.StudentEmailRequest studentEmailRequest,
                                                   BindingResult bindingResult){

        MemberDTO.StudentEmailResponse response = new MemberDTO.StudentEmailResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode("FAIL");
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }

        if(!memberService.memberEmailOverlabCheck(studentEmailRequest.getStudentEmail())){
            response.setResultCode("SUCCESS");
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }else{
            response.setResultCode("SUCCESS");
            response.setResultMessage(ResponseFailMessage.H000002.getMessage());
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/api/accounts")
    public ResponseEntity studentSignUp(@Valid @RequestBody MemberDTO.StudentSingUpRequest studentSingUpRequest,
                                        BindingResult bindingResult){

        MemberDTO.StudentSignUpResponse response = new MemberDTO.StudentSignUpResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode("ERROR");
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
        }else{
            boolean memberSingPossibleWhether = memberService.memberSignPossibleWhether(Integer.parseInt(studentSingUpRequest.getStudentNumber()));
            boolean memberEmailOverlabCheck = memberService.memberEmailOverlabCheck(studentSingUpRequest.getEmail());

            if(memberSingPossibleWhether && !memberEmailOverlabCheck){
                MemberEntity memberEntity = MemberEntity.builder()
                        .stdNum(Integer.parseInt(studentSingUpRequest.getStudentNumber()))
                        .name(studentSingUpRequest.getName())
                        .email(studentSingUpRequest.getEmail())
                        .password(passwordEncoder.encode(studentSingUpRequest.getPassword()))
                        .nickname(studentSingUpRequest.getNickName())
                        .build();

                memberService.memberSignUp(memberEntity);

                response.setResultCode("SUCCESS");
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }else{
                response.setResultCode("ERROR");
                response.setResultMessage(ResponseFailMessage.H000003.getMessage());
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }
        }
    }
}
