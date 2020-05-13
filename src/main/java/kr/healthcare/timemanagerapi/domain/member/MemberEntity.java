package kr.healthcare.timemanagerapi.domain.member;

import kr.healthcare.timemanagerapi.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity extends BaseTimeEntity {

    @Id
    @Column(length = 8)
    private long stdNum;

    @Column(length = 45, unique = true, nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String password;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45)
    private String nickname;

    @Column(nullable = false, columnDefinition = "varchar(1) default 'U'")
    private String auth;

    private LocalDateTime deleteDate;

    @Column(nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String deleteYn;

    private String imagePath;

    private String imageKey;

    private String imageFilename;

    @Column(length = 500)
    private String token;

    @PrePersist
    public void defaultValueCheck(){
        this.auth = (this.auth == null) ? this.auth = "U" : this.auth;
        this.deleteYn = (this.deleteYn == null) ? this.deleteYn = "N" : this.deleteYn;
    }

    @Builder
    public MemberEntity(long stdNum,
                        String email,
                        String password,
                        String name,
                        String nickname,
                        String imagePath,
                        String imageKey,
                        String imageFilename,
                        String token,
                        String auth){
        this.stdNum=stdNum;
        this.email=email;
        this.password=password;
        this.name=name;
        this.nickname=nickname;
        this.imagePath=imagePath;
        this.imageKey=imageKey;
        this.imageFilename=imageFilename;
        this.token=token;
        this.auth=auth;
    }

}
