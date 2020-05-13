package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class MemberDTO {

    @Getter
    @Setter
    @ToString
    public static class StudentNumberRequest{
        @NotEmpty(message = "학번은 필수값 입니다.")
        @Pattern(regexp = "(^[0-9]{8}$)", message = "학번은 8자리 입니다.")
        private String studentNumber;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentNumberResponse{
        private String resultCode;
        private String resultMessage;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentEmailRequest{
        @NotEmpty(message = "이메일은 필수값 입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String studentEmail;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentEmailResponse{
        private String resultCode;
        private String resultMessage;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentSingUpRequest{
        @NotEmpty(message = "학번은 필수값 입니다.")
        @Pattern(regexp = "(^[0-9]{8}$)", message = "학번은 8자리 입니다.")
        private String studentNumber;

        @NotEmpty(message = "이름은 필수값 입니다.")
        @Length(max = 10, message = "이름은 최대 10자 입니다.")
        private String name;

        @NotEmpty(message = "이메일은 필수값 입니다.")
        @Length(max = 45, message = "이메일은 최대 45자 입니다.")
        @Email
        private String email;

        @NotEmpty(message = "비밀번호는 필수값 입니다.")
        @Length(min = 8, max = 40, message = "비밀번호는 최소 8자리에서 최대 40자리 입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*?,./\\\\\\\\<>|_-[+]=\\\\`~\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}])[A-Za-z[0-9]!@#$%^&*?,./\\\\\\\\<>|_-[+]=\\\\`~\\\\(\\\\)\\\\[\\\\]\\\\{\\\\}]{8,40}$", message = "비밀번호는 문자, 특수문자, 숫자를 포함해야 합니다.")
        private String password;

        @Length(message = "별명은 최대 10자 입니다.")
        private String nickName;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentSignUpResponse{
        private String resultCode;
        private String resultMessage;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentLoginRequest{
        @NotEmpty(message = "이메일은 필수값 입니다.")
        @Email
        private String email;
        @NotEmpty(message = "비밀번호는 필수값 입니다.")
        private String password;
    }

    @Getter
    @Setter
    @ToString
    public static class StudentLoginResponse{
        private String resultCode;
        private String resultMessage;
        private String token;
    }

}
