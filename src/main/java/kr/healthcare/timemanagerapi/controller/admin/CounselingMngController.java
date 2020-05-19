package kr.healthcare.timemanagerapi.controller.admin;

import kr.healthcare.timemanagerapi.constant.ResponseFailMessage;
import kr.healthcare.timemanagerapi.domain.consulting.manage.CounselingManageEntity;
import kr.healthcare.timemanagerapi.domain.consulting.manage.CounselingManageRepository;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import kr.healthcare.timemanagerapi.dto.admin.CounselingMngDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class CounselingMngController {

    @NonNull
    MemberRepositroy memberRepositroy;

    @NonNull
    CounselingManageRepository counselingManageRepository;

    @PostMapping("/counseling/register")
    public ResponseEntity counselingRegister(@Valid @RequestBody CounselingMngDTO.counselingRegisterRequest counselingRegisterRequest,
                                             BindingResult bindingResult,
                                             @RequestHeader(value = "Authentication") String token){

        CounselingMngDTO.counselingRegisterResponse response = new CounselingMngDTO.counselingRegisterResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.toString());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<CounselingMngDTO.counselingRegisterResponse>(response, HttpStatus.OK);
        }else{
            MemberEntity memberEntity = memberRepositroy.findByToken(token);
            CounselingManageEntity counselingManageEntity = CounselingManageEntity
                                                                        .builder()
                                                                        .adminNum(memberEntity.getStdNum())
                                                                        .semester(counselingRegisterRequest.getSemester())
                                                                        .startDate(LocalDate.parse(counselingRegisterRequest.getStartDate()))
                                                                        .endDate(LocalDate.parse(counselingRegisterRequest.getEndDate()))
                                                                        .member(memberEntity)
                                                                        .build();
            counselingManageRepository.save(counselingManageEntity);
            response.setResultCode(ResponseFailMessage.SUCCESS.toString());
            return new ResponseEntity<CounselingMngDTO.counselingRegisterResponse>(response, HttpStatus.OK);
        }
    }
}
