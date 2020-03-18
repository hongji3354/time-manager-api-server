package kr.healthcare.timemanagerapi.service.member;

import kr.healthcare.timemanagerapi.domain.member.Member;
import kr.healthcare.timemanagerapi.domain.member.MemberRepository;
import kr.healthcare.timemanagerapi.dto.member.MemberRegisterDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void memberRegister(MemberRegisterDTO memberRegisterDTO){

        memberRegisterDTO.setMemberPassword(passwordEncoder.encode(memberRegisterDTO.getMemberPassword()));

        Member member = memberRepository.save(Member.builder()
                                                    .admissionNumber(memberRegisterDTO.getAdmissionNumber())
                                                    .memberName(memberRegisterDTO.getAdmissionNumber())
                                                    .memberGender(memberRegisterDTO.getMemberGender())
                                                    .memberId(memberRegisterDTO.getMemberId())
                                                    .memberPassword(memberRegisterDTO.getMemberPassword())
                                                    .auth("USER")
                                                    .build());
    }


}
