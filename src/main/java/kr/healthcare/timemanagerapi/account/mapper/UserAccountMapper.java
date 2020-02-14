package kr.healthcare.timemanagerapi.account.mapper;

import kr.healthcare.timemanagerapi.account.vo.UserAccountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserAccountMapper {

    int findByStudentNumber(String studentNumber);
    int findByStudentId(String memberId);
    Map<String,String> findByAccount(String memberId);
    int persistByAccount(UserAccountVO insertVO);
    int updateByAccountToken(Map<String,String> accountUpdateData);
    int findByToken(String token);

}
