package kr.healthcare.timemanagerapi.controller.member;

import kr.healthcare.timemanagerapi.dto.member.AdmissionNumberDTO;
import kr.healthcare.timemanagerapi.dto.member.MemberIdDTO;
import kr.healthcare.timemanagerapi.dto.member.MemberOverlabCheckErrorResultDTO;
import kr.healthcare.timemanagerapi.dto.member.MemberOverlabCheckResultDTO;
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

    @GetMapping("/hello")
    public String helloMember(){
        return "helloMember";
    }

    @GetMapping("/api/members/admission-number")
    public ResponseEntity admissionNumberOverlabCheck(@RequestBody @Valid AdmissionNumberDTO admissionNumberDTO,
                                                      BindingResult bindingResult){

        MemberOverlabCheckResultDTO memberOverlabCheckResultDTO = new MemberOverlabCheckResultDTO();

        if(bindingResult.hasErrors()){
            MemberOverlabCheckErrorResultDTO memberOverlabCheckErrorResultDTO = new MemberOverlabCheckErrorResultDTO();
            memberOverlabCheckErrorResultDTO.setResultInfo("ERROR");
            memberOverlabCheckErrorResultDTO.setResultCode("E000001");
            return ResponseEntity.ok(memberOverlabCheckErrorResultDTO);
        }

        boolean admissionNumberOverlabWhether = memberSerivce.admissionNumberOverlabCheckFromMember(admissionNumberDTO.getAdmissionNumber());

        if(!admissionNumberOverlabWhether){
            memberOverlabCheckResultDTO.setResultInfo("SUCCESS");
            memberOverlabCheckResultDTO.setSearchCount(0);
            return ResponseEntity.ok(memberOverlabCheckResultDTO);
        }else{
            memberOverlabCheckResultDTO.setResultInfo("SUCCESS");
            memberOverlabCheckResultDTO.setSearchCount(1);
            return ResponseEntity.ok(memberOverlabCheckResultDTO);
        }
    }

    @GetMapping("/api/members/member-id")
    public ResponseEntity memberIdOverlabCheck(@RequestBody @Valid MemberIdDTO memberIdDTO,
                                               BindingResult bindingResult){

        MemberOverlabCheckResultDTO memberOverlabCheckResultDTO = new MemberOverlabCheckResultDTO();

        if(bindingResult.hasErrors()){
            MemberOverlabCheckErrorResultDTO memberOverlabCheckErrorResultDTO = new MemberOverlabCheckErrorResultDTO();
            memberOverlabCheckErrorResultDTO.setResultInfo("ERROR");
            memberOverlabCheckErrorResultDTO.setResultCode("E000002");
            return ResponseEntity.ok(memberOverlabCheckErrorResultDTO);
        }

        boolean memberIdOverlabWhether = memberSerivce.memberIdOverlabCheckFromMember(memberIdDTO.getMemberId());

        if(!memberIdOverlabWhether){
            memberOverlabCheckResultDTO.setResultInfo("SUCCESS");
            memberOverlabCheckResultDTO.setSearchCount(0);
            return ResponseEntity.ok(memberOverlabCheckResultDTO);
        }else{
            memberOverlabCheckResultDTO.setResultInfo("SUCCESS");
            memberOverlabCheckResultDTO.setSearchCount(1);
            return ResponseEntity.ok(memberOverlabCheckResultDTO);
        }

    }



}
