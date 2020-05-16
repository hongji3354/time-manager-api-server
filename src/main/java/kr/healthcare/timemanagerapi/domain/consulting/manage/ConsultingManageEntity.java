package kr.healthcare.timemanagerapi.domain.consulting.manage;

import kr.healthcare.timemanagerapi.domain.BaseTimeEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
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
public class ConsultingManageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private long idx;

    @Column(length = 45, unique = true)
    private String semester;

    @Column(length = 8, unique = true)
    private long adminNum;

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

    public void fkSetting(MemberEntity member){
        this.member = member;
    }

    @PrePersist
    public void defaultValueCheck(){
        this.deleteYn = (this.deleteYn == null) ? this.deleteYn = "N" : this.deleteYn;
        this.forceEndYn = (this.forceEndYn == null) ? this.forceEndYn = "N" : this.forceEndYn;
    }

    @Builder
    public ConsultingManageEntity(LocalDate startDate,
                                  LocalDate endDate,
                                  String semester,
                                  long adminNum,
                                  String forceEndYn,
                                  LocalDateTime deleteDate,
                                  String deleteYn){
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester=semester;
        this.adminNum=adminNum;
        this.forceEndYn = forceEndYn;
        this.deleteDate = deleteDate;
        this.deleteYn = deleteYn;
    }
}
