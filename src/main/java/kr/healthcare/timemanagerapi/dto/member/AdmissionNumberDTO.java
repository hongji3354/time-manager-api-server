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
public class AdmissionNumberDTO {

    @Length(min = 8, max = 8)
    @NotEmpty
    private String admissionNumber;
}
