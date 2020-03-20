package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.dto.member.*;
import kr.healthcare.timemanagerapi.service.member.MemberSerivce;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class MemberController {

    @NonNull
    MemberSerivce memberSerivce;

    @GetMapping("/api/members/admission-number")
    public ResponseEntity admissionNumberOverlabCheck(@RequestBody @Valid AdmissionNumberRequestDTO admissionNumberRequestDTO,
                                                      BindingResult bindingResult){

        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();

        if(bindingResult.hasErrors()){
            MemberErrorResponseDTO memberErrorResponseDTO = new MemberErrorResponseDTO();
            memberErrorResponseDTO.setResultInfo("ERROR");
            memberErrorResponseDTO.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(memberErrorResponseDTO);
        }

        boolean admissionNumberOverlabWhether = memberSerivce.admissionNumberOverlabCheckFromMember(admissionNumberRequestDTO.getAdmissionNumber());

        if(!admissionNumberOverlabWhether){
            memberResponseDTO.setResultInfo("SUCCESS");
            memberResponseDTO.setSearchCount(0);
            return ResponseEntity.ok(memberResponseDTO);
        }else{
            memberResponseDTO.setResultInfo("SUCCESS");
            memberResponseDTO.setSearchCount(1);
            return ResponseEntity.ok(memberResponseDTO);
        }
    }

    @GetMapping("/api/members/member-id")
    public ResponseEntity memberIdOverlabCheck(@RequestBody @Valid MemberIdRequestDTO memberIdRequestDTO,
                                               BindingResult bindingResult){

        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();

        if(bindingResult.hasErrors()){
            MemberErrorResponseDTO memberErrorResponseDTO = new MemberErrorResponseDTO();
            memberErrorResponseDTO.setResultInfo("ERROR");
            memberErrorResponseDTO.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(memberErrorResponseDTO);
        }

        boolean memberIdOverlabWhether = memberSerivce.memberIdOverlabCheckFromMember(memberIdRequestDTO.getMemberId());

        if(!memberIdOverlabWhether){
            memberResponseDTO.setResultInfo("SUCCESS");
            memberResponseDTO.setSearchCount(0);
            return ResponseEntity.ok(memberResponseDTO);
        }else{
            memberResponseDTO.setResultInfo("SUCCESS");
            memberResponseDTO.setSearchCount(1);
            return ResponseEntity.ok(memberResponseDTO);
        }

    }

    @PostMapping("/api/members")
    public ResponseEntity memberRegister(@RequestBody @Valid MemberRegisterDTO memberRegisterDTO,
                                         BindingResult bindingResult){

        MemberErrorResponseDTO memberErrorResponseDTO = new MemberErrorResponseDTO();

        if(bindingResult.hasErrors()){
            memberErrorResponseDTO.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(memberErrorResponseDTO);
        }

        boolean success = memberSerivce.memberRegister(memberRegisterDTO);

        if(!success){
            memberErrorResponseDTO.setResultInfo("ERROR");
            memberErrorResponseDTO.setResultMessage("동일한 학번 또는 아이디가 존재합니다.");
            return ResponseEntity.ok(memberErrorResponseDTO);
        }else{
            Map<String, String> result = new HashMap<>();
            result.put("resultInfo","SUCCESS");
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/api/members/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDTO memberLoginDTO,
                                BindingResult bindingResult){

        MemberErrorResponseDTO memberErrorResponseDTO = new MemberErrorResponseDTO();

        if(bindingResult.hasErrors()){
            memberErrorResponseDTO.setResultInfo("ERROR");
            memberErrorResponseDTO.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(memberErrorResponseDTO);
        }


        Map<String,String> result = new HashMap<>();

        memberSerivce.memberRegister(memberRegisterDTO);

        result.put("RESULT_INFO","SUCCESS");

        return ResponseEntity.ok(result);


    }
}
