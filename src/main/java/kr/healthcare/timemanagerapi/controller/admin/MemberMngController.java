package kr.healthcare.timemanagerapi.controller.admin;

import kr.healthcare.timemanagerapi.constant.Authority;
import kr.healthcare.timemanagerapi.constant.ResponseFailMessage;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import kr.healthcare.timemanagerapi.dto.admin.MemberMngDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class MemberMngController {

    @NonNull
    private MemberRepositroy memberRepositroy;

    @NonNull
    private ModelMapper modelMapper;

    @GetMapping("/member/list")
    public ResponseEntity memberList(){
        MemberMngDTO.MemberListResponse response = new MemberMngDTO.MemberListResponse();

        List<MemberMngDTO.MemberInformation> memberInformations = modelMapper.map(memberRepositroy.findAllByAuthOrderByCreatedDateDesc(Authority.U.toString()), new TypeToken<List<MemberMngDTO.MemberInformation>>(){}.getType()); ;

        response.setSearchCount(memberInformations.size());
        response.setMemberList(memberInformations);

        return new ResponseEntity<MemberMngDTO.MemberListResponse>(response, HttpStatus.OK);
    }

    @PatchMapping("/member/permission/approve")
    public ResponseEntity memberPermissionApprove(@Valid @RequestBody MemberMngDTO.MemberPermissionApprovalRequest memberPermissionApprovalRequest,
                                                  BindingResult bindingResult){
        MemberMngDTO.MemberPermissionApprovalResponse response = new MemberMngDTO.MemberPermissionApprovalResponse();

        if(bindingResult.hasErrors()){
            response.setResultCode(ResponseFailMessage.FAIL.getMessage());
            response.setResultMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<MemberMngDTO.MemberPermissionApprovalResponse>(response,HttpStatus.OK);
        }else{
            MemberEntity memberEntity = memberRepositroy.findByStdNum(memberPermissionApprovalRequest.getStudentNumber());
            if(memberEntity != null){
                if(Authority.U.toString().equals(memberEntity.getAuth())){
                    memberRepositroy.updateMemberPermissionApprove(memberPermissionApprovalRequest.getStudentNumber());
                    response.setResultCode(ResponseFailMessage.SUCCESS.getMessage());
                    return new ResponseEntity<MemberMngDTO.MemberPermissionApprovalResponse>(response,HttpStatus.OK);
                }else{
                    response.setResultCode(ResponseFailMessage.FAIL.getMessage());
                    response.setResultMessage(ResponseFailMessage.H000009.getMessage());
                    return new ResponseEntity<MemberMngDTO.MemberPermissionApprovalResponse>(response,HttpStatus.OK);
                }
            }else{
                response.setResultCode(ResponseFailMessage.FAIL.getMessage());
                response.setResultMessage(ResponseFailMessage.H000008.getMessage());
                return new ResponseEntity<MemberMngDTO.MemberPermissionApprovalResponse>(response,HttpStatus.OK);
            }
        }
    }
}
