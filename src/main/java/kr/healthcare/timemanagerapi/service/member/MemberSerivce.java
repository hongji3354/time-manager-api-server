package kr.healthcare.timemanagerapi.service.member;

import kr.healthcare.timemanagerapi.domain.member.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    @NonNull
    MemberRepository memberRepository;

    public boolean admissionNumberOverlabCheckFromMember(String admissionNumber){
        int admissionNumberCount = memberRepository.countByAdmissionNumber(admissionNumber);

        if(admissionNumberCount == 0){
            return false;
        }else{
            return true;
        }
    }


}
