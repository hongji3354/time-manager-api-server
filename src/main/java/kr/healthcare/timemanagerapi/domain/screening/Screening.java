package kr.healthcare.timemanagerapi.domain.screening;

import kr.healthcare.timemanagerapi.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_screening")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Screening extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Column(length = 20, nullable = false)
    private String memberId;

    private int perfectionism;
    private int dreamer;
    private int worrier;
    private int resister;
    private int thriller;
    private int workaholic;
}
