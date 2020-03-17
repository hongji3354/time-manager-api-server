package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.dto.member.*;
import kr.healthcare.timemanagerapi.service.member.MemberSerivce;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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



}
