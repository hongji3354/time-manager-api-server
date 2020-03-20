package kr.healthcare.timemanagerapi.service.member;

import kr.healthcare.timemanagerapi.domain.member.Member;
import kr.healthcare.timemanagerapi.domain.member.MemberRepository;
import kr.healthcare.timemanagerapi.dto.member.MemberLoginDTO;
import kr.healthcare.timemanagerapi.dto.member.MemberRegisterDTO;
import kr.healthcare.timemanagerapi.util.JwtTokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    @NonNull
    MemberRepository memberRepository;

    @NonNull
    PasswordEncoder passwordEncoder;

    public boolean admissionNumberOverlabCheckFromMember(String admissionNumber){
        int admissionNumberCount = memberRepository.countByAdmissionNumber(admissionNumber);

        if(admissionNumberCount == 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean memberIdOverlabCheckFromMember(String memberId){
        int memberIdCount = memberRepository.countByMemberId(memberId);

        if(memberIdCount == 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean memberRegister(MemberRegisterDTO memberRegisterDTO){

        boolean admissionNumberOverlab = this.admissionNumberOverlabCheckFromMember(memberRegisterDTO.getAdmissionNumber());
        boolean memberIdOverlab = this.memberIdOverlabCheckFromMember(memberRegisterDTO.getMemberId());

        if(admissionNumberOverlab && memberIdOverlab){
            return false;
        }else{
            memberRegisterDTO.setMemberPassword(passwordEncoder.encode(memberRegisterDTO.getMemberPassword()));

            Member member = memberRepository.save(Member.builder()
                    .admissionNumber(memberRegisterDTO.getAdmissionNumber())
                    .memberName(memberRegisterDTO.getAdmissionNumber())
                    .memberGender(memberRegisterDTO.getMemberGender())
                    .memberId(memberRegisterDTO.getMemberId())
                    .memberPassword(memberRegisterDTO.getMemberPassword())
                    .auth("USER")
                    .build());

            return true;
        }


    }


}
