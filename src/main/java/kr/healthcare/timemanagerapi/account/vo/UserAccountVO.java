package kr.healthcare.timemanagerapi.account.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserAccountVO {

    @Length(min = 8, max = 8, message = "학번은 8글자 입니다.")
    private String studentNumber;
    @Length(min = 3, max = 20, message = "아이디는 최소 3자리 이상 최대 20자리 이하입니다.")
    private String memberId;
    @Length(min = 10, max = 20, message = "비밀번호는 최소 10자리 이상 최대 20자리 이하입니다.")
    private String memberPw;
    @Length(min = 1, max = 1, message = "성별은 M 아니면 F 입니다.")
    private String memberGender;
    @Length(min = 2, max = 5, message = "이름은 최소 2자리 이상 최대 5자리 이하입니다.")
    private String memberName;

    private String salt;
}
