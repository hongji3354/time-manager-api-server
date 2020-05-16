package kr.healthcare.timemanagerapi.domain.consulting.manage;

import kr.healthcare.timemanagerapi.domain.BaseTimeEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "COUNSELING_TBL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CounselingManageEntity extends BaseTimeEntity {

    @Id
    private String idx;

    @Column(length = 8)
    private String adminNum;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 1, nullable = false)
    private String forceEndYn;

    private LocalDateTime deleteDate;

    @Column(length = 1)
    private String deleteYn;

    @ManyToOne
    @JoinColumn(name = "admin_number")
    private MemberEntity member;

    @Transient
    private String semester;

    @PrePersist
    public void defaultValueCheck(){
        this.deleteYn = (this.deleteYn == null) ? this.deleteYn = "N" : this.deleteYn;
        this.forceEndYn = (this.forceEndYn == null) ? this.forceEndYn = "N" : this.forceEndYn;
    }

    @PreUpdate
    public void defaultValueUpdateCheck(){
        this.deleteYn = (this.deleteYn == null) ? this.deleteYn = "N" : this.deleteYn;
        this.forceEndYn = (this.forceEndYn == null) ? this.forceEndYn = "N" : this.forceEndYn;
    }

    @Builder
    public CounselingManageEntity(LocalDate startDate,
                                  LocalDate endDate,
                                  String semester,
                                  String adminNum,
                                  String forceEndYn,
                                  LocalDateTime deleteDate,
                                  String deleteYn,
                                  MemberEntity member){
        this.idx = semester+adminNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester=semester;
        this.adminNum=adminNum;
        this.forceEndYn = forceEndYn;
        this.deleteDate = deleteDate;
        this.deleteYn = deleteYn;
        this.member = member;
    }
}
