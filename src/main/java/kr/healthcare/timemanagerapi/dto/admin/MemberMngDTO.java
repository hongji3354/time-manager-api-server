package kr.healthcare.timemanagerapi.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMngDTO {

    @Getter
    @Setter
    @ToString
    public static class MemberInformation {
        private String stdNum;
        private String email;
        private String name;
        private String nickname;
        private String acceptYn;
        private LocalDateTime createdDate;
    }

    @Getter
    @Setter
    @ToString
    public static class MemberListResponse {
        private int searchCount;
        private List<MemberInformation> memberList;
    }

    @Getter
    @Setter
    @ToString
    public static class MemberPermissionApprovalRequest {
        @NotEmpty(message = "학번은 필수값 입니다.")
        @Pattern(regexp = "(^[0-9]{8}$)", message = "학번은 8자리 입니다.")
        private String studentNumber;
    }

    @Getter
    @Setter
    @ToString
    public static class MemberPermissionApprovalResponse {
        private String resultCode;
        private String resultMessage;
    }
}
