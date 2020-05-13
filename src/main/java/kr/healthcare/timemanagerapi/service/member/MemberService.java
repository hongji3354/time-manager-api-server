package kr.healthcare.timemanagerapi.service.member;

import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import kr.healthcare.timemanagerapi.dto.member.MemberDTO;
import kr.healthcare.timemanagerapi.util.JwtTokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    @NonNull
    MemberRepositroy memberRepositroy;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    public boolean memberSignPossibleWhether(int studentNumber){
        return (memberRepositroy.countAllByStdNum(studentNumber) == 0) ? true : false;
    }

    public boolean memberEmailOverlabCheck(String email){
        return (memberRepositroy.countAllByEmail(email) != 0) ? true : false;
    }

    public void memberSignUp(MemberEntity memberEntity){
        memberRepositroy.save(memberEntity);
    }

    @Transactional
    public String memberLogin(MemberDTO.StudentLoginRequest studentLoginRequest){
        MemberEntity memberEntity = memberRepositroy.findByEmail(studentLoginRequest.getEmail());

        if(passwordEncoder.matches(studentLoginRequest.getPassword(), memberEntity.getPassword())){
            Map<String, String> claim = new HashMap<>();
            String token = JwtTokenUtil.deGenerateToken();

            memberRepositroy.updateToken(token, memberEntity.getEmail());

            return token;
        }else{
            return "";
        }
    }
}
