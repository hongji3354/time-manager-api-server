package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.constant.Authority;
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
    private MemberRepositroy memberRepositroy;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/api/accounts/student-number")
    public ResponseEntity studentNumberOverlapCheck(@Valid MemberDTO.StudentNumberRequest studentNumberRequest,
                                                                                     BindingResult bindingResult){
        MemberDTO.StudentNumberResponse response = new MemberDTO.StudentNumberResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }

        if(memberRepositroy.existsByStdNum(Integer.parseInt(studentNumberRequest.getStudentNumber()))){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(ResponseFailMessage.H000001.getMessage());
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }else{
            response.setResultCode(ResponseFailMessage.SUCCESS.toString());
            return new ResponseEntity<MemberDTO.StudentNumberResponse>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/api/accounts/student-email")
    public ResponseEntity studentEmaliOverlapCheck(@Valid MemberDTO.StudentEmailRequest studentEmailRequest,
                                                   BindingResult bindingResult){

        MemberDTO.StudentEmailResponse response = new MemberDTO.StudentEmailResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }

        if(memberRepositroy.existsByEmail(studentEmailRequest.getStudentEmail())){
            response.setResultCode(ResponseFailMessage.SUCCESS.toString());
            response.setResultMessage(ResponseFailMessage.H000002.getMessage());
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }else{
            response.setResultCode(ResponseFailMessage.SUCCESS.toString());
            return new ResponseEntity<MemberDTO.StudentEmailResponse>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/api/accounts")
    public ResponseEntity studentSignUp(@Valid @RequestBody MemberDTO.StudentSingUpRequest studentSingUpRequest,
                                        BindingResult bindingResult){

        MemberDTO.StudentSignUpResponse response = new MemberDTO.StudentSignUpResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
        }else{
            boolean memberSingPossibleWhether = memberRepositroy.existsByStdNum(Integer.parseInt(studentSingUpRequest.getStudentNumber()));
            boolean memberEmailOverlabCheck = memberRepositroy.existsByEmail(studentSingUpRequest.getEmail());

            if(!memberSingPossibleWhether && !memberEmailOverlabCheck){
                MemberEntity memberEntity = MemberEntity.builder()
                        .stdNum(Integer.parseInt(studentSingUpRequest.getStudentNumber()))
                        .name(studentSingUpRequest.getName())
                        .email(studentSingUpRequest.getEmail())
                        .password(passwordEncoder.encode(studentSingUpRequest.getPassword()))
                        .nickname(studentSingUpRequest.getNickName())
                        .auth(Authority.U.toString())
                        .build();

                memberRepositroy.save(memberEntity);

                response.setResultCode(ResponseFailMessage.SUCCESS.toString());
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }else{
                response.setResultCode(ResponseFailMessage.FAIL.toString());
                response.setResultMessage(ResponseFailMessage.H000003.getMessage());
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity studentLogin(@Valid @RequestBody MemberDTO.StudentLoginRequest studentLoginRequest,
                                       BindingResult bindingResult){

        MemberDTO.StudentLoginResponse response = new MemberDTO.StudentLoginResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentLoginResponse>(response, HttpStatus.OK);
        }else{
            String token = memberService.memberLogin(studentLoginRequest);
            if("".equals(token)){
                response.setResultCode(ResponseFailMessage.FAIL.toString());
                response.setResultMessage(ResponseFailMessage.H000004.getMessage());
                return new ResponseEntity<MemberDTO.StudentLoginResponse>(response, HttpStatus.OK);
            }else{
                response.setResultCode(ResponseFailMessage.SUCCESS.toString());
                response.setToken(token);
                return new ResponseEntity<MemberDTO.StudentLoginResponse>(response, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/api/accounts/admin")
    public ResponseEntity adminSignUp(@Valid @RequestBody MemberDTO.StudentSingUpRequest studentSingUpRequest,
                                        BindingResult bindingResult){

        MemberDTO.StudentSignUpResponse response = new MemberDTO.StudentSignUpResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
        }else{
            boolean memberSingPossibleWhether = memberRepositroy.existsByStdNum(Integer.parseInt(studentSingUpRequest.getStudentNumber()));
            boolean memberEmailOverlabCheck = memberRepositroy.existsByEmail(studentSingUpRequest.getEmail());

            if(!memberSingPossibleWhether && !memberEmailOverlabCheck){
                MemberEntity memberEntity = MemberEntity.builder()
                        .stdNum(Integer.parseInt(studentSingUpRequest.getStudentNumber()))
                        .name(studentSingUpRequest.getName())
                        .email(studentSingUpRequest.getEmail())
                        .password(passwordEncoder.encode(studentSingUpRequest.getPassword()))
                        .nickname(studentSingUpRequest.getNickName())
                        .auth(Authority.A.toString())
                        .build();

                memberRepositroy.save(memberEntity);

                response.setResultCode(ResponseFailMessage.SUCCESS.toString());
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }else{
                response.setResultCode(ResponseFailMessage.FAIL.toString());
                response.setResultMessage(ResponseFailMessage.H000003.getMessage());
                return new ResponseEntity<MemberDTO.StudentSignUpResponse>(response, HttpStatus.OK);
            }
        }
    }
}
