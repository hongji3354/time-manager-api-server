package kr.healthcare.timemanagerapi.domain.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @Column(length = 8, nullable = false)
    private String admissionNumber;

    @Column(length = 20, nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberPassword;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 1, nullable = false)
    private String memberGender;

    private String token;

    @Builder
    public Member(String admissionNumber,
                  String memberId,
                  String memberPassword,
                  String memberName,
                  String memberGender){
        this.admissionNumber=admissionNumber;
        this.memberId=memberId;
        this.memberPassword=memberPassword;
        this.memberName=memberName;
        this.memberGender=memberGender;
    }


}
