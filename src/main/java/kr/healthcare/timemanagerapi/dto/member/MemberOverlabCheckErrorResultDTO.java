package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberOverlabCheckErrorResultDTO {

    private String resultInfo;
    private String resultCode;
}
