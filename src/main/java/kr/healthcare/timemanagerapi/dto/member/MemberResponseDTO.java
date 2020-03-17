package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberResponseDTO {

    private String resultInfo;
    private int searchCount;
}
