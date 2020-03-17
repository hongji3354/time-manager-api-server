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
public class MemberRegisterDTO {

    @NotEmpty(message = "입학번호(학번)을 입력해주세요.")
    @Length(min = 8, max = 8, message = "입학번호(학번)은 8자리 입니다.")
    private String admissionNumber;
    @NotEmpty(message = "이름을 입력해주세요.")
    @Length(min = 2, max = 10, message = "이름은 최소 2자리 쵀대 10자리 입니다.")
    private String name;
    @NotEmpty(message = "성별을 선택해주세요.")
    @Length(min = 1, max = 1)
    @Pattern(regexp = "[MW]")
    private String memberGender;
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Length(min = 3, max = 20, message = "아이디는 최소 2자리 최대 20자리 입니다.")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    //영문(대소문자 구분), 숫자, 특수문자 조합, 8~20자리
    @Pattern(regexp = "^(?=.*\\\\d)(?=.*[~`!@#$%\\\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{8,20}$", message = "비밀번호는 영문(대소문자 구분), 숫자, 특수문자를 조합해야 하며 최소 8 자리 최대 20자리 입니다.")
    private String memberPassword;

}
