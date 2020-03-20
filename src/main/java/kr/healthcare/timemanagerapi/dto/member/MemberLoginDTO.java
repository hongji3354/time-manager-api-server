package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MemberLoginDTO {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String memberPassword;
}
