package kr.healthcare.timemanagerapi.service.member;

import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    @NonNull
    MemberRepositroy memberRepositroy;

    public boolean memberSignPossibleWhether(int studentNumber){
        return (memberRepositroy.countAllByStdNum(studentNumber) == 0) ? true : false;
    }

    public boolean memberEmailOverlabCheck(String email){
        return (memberRepositroy.countAllByEmail(email) != 0) ? true : false;
    }
}
