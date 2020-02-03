package kr.healthcare.timemanagerapi.service.user.account;

import kr.healthcare.timemanagerapi.mapper.user.account.UserAccountMapper;
import kr.healthcare.timemanagerapi.util.SHA256;
import kr.healthcare.timemanagerapi.domain.user.account.UserAccountVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    @NonNull
    private UserAccountMapper userAccountMapper;

    public int findByStudentNumberDuplicateCheck(String studentNumber){
        return userAccountMapper.findByStudentNumberCount(studentNumber);
    }

    public int findByMemberIdDuplicateCheck(String studentId){
        return userAccountMapper.findByStudentIdCount(studentId);
    }

    public int persistByStudentAccount(UserAccountVO insertVO){
        String salt = SHA256.generateSalt();
        String encryptPassword = SHA256.getEncrypt(insertVO.getMemberPw(),salt);

        insertVO.setMemberPw(encryptPassword);
        insertVO.setSalt(salt);

        return userAccountMapper.persistByStudentAccount(insertVO);
    }

}
