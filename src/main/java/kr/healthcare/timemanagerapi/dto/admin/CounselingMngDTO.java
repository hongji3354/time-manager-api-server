package kr.healthcare.timemanagerapi.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class CounselingMngDTO {

    @Getter
    @Setter
    @ToString
    public static class counselingRegisterRequest{
        @NotEmpty(message = "상담시작일정은 필수 입니다.")
        @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "날짜형식은 YYYY-MM-DD 입니다.")
        private String startDate;
        @NotEmpty(message = "상담종료일정은 필수 입니다.")
        @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "날짜형식은 YYYY-MM-DD 입니다.")
        private String endDate;
        @NotEmpty(message = "학기는 필수 입니다.")
        @Pattern(regexp = "^[0-9]{6}$", message = "연도와 학기는(202001 또는 202002)로 입력해 주셔야 합니다.")
        private String semester;
    }

    @Getter
    @Setter
    @ToString
    public static class counselingRegisterResponse{
        private String resultCode;
        private String resultMessage;
    }
}
