package kr.healthcare.timemanagerapi.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 * MappedSuperclass는 BaseTimeEntity를 상속할 경우 필드들도 컬럼으로 인식하도록 설정
 * @EntityListeners(AuditingEntityListener.class)는 BaseTimeEntity 클래스에 Auditing 기능을 포항
 *
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // @CreatedDate는 Entity가 저장될 때 시간이 자동 저장
    @CreatedDate
    // updatable이 true 일 시 update 쿼리 발생시 createdDate에 null이 들어가서 update는 하지 않음.
    @Column(updatable = false)
    private LocalDateTime createdDate;

    // @LastModifiedDate는 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifyDate;
}
