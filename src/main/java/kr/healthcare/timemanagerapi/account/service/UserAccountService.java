package kr.healthcare.timemanagerapi.account.service;

import kr.healthcare.timemanagerapi.account.mapper.UserAccountMapper;
import kr.healthcare.timemanagerapi.jwt.JWT;
import kr.healthcare.timemanagerapi.util.SHA256;
import kr.healthcare.timemanagerapi.account.vo.UserAccountVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    @NonNull
    private UserAccountMapper userAccountMapper;

    public boolean studentNumberOverLabCheck(String studentNumber){

        if(userAccountMapper.findByStudentNumber(studentNumber) == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean memberIdOverLabCheck(String memberId){

        if(userAccountMapper.findByStudentId(memberId) == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean accountRegister(UserAccountVO insertVO){
        String salt = SHA256.generateSalt();
        String encryptPassword = SHA256.getEncrypt(insertVO.getMemberPw(),salt);
        insertVO.setMemberPw(encryptPassword);
        insertVO.setSalt(salt);

        if(userAccountMapper.persistByStudentAccount(insertVO) != 0){
            return true;
        }else{
            return false;
        }
    }

    public String login(UserAccountVO searchVO){
        Map<String,String> account = userAccountMapper.findByAccount(searchVO.getMemberId());

        if((SHA256.getEncrypt(searchVO.getMemberPw(),account.get("SALT")).equals(account.get("MEMBER_PW")))){
            return JWT.getToken(searchVO);
        }else{
            return "";
        }
    }
}
