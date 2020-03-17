package kr.healthcare.timemanagerapi.dto.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdmissionNumberRequestDTO {

    @NotEmpty(message = "입학번호(학번)을 입력해주세요.")
    @Length(min = 8, max = 8, message = "입학번호(학번)은 8자리 입니다.")
    private String admissionNumber;
}
