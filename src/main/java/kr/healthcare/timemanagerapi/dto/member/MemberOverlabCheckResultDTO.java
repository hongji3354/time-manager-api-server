package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberOverlabCheckResultDTO {

    private String resultInfo;
    private int searchCount;
}
