package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
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

}
