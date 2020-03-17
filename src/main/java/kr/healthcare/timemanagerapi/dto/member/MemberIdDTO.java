package kr.healthcare.timemanagerapi.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberIdDTO {

    @Length(min = 3, max = 20)
    @NotEmpty
    private String memberId;
}
