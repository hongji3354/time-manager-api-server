package kr.healthcare.timemanagerapi.mapper.user.account;

import kr.healthcare.timemanagerapi.domain.user.account.UserAccountVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper {

    public int findByStudentNumberCount(String studentNumber);
    public int findByStudentIdCount(String student);
    public int persistByStudentAccount(UserAccountVO insertVO);

}
