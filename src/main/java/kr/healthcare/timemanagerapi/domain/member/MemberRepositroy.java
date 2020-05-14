package kr.healthcare.timemanagerapi.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.Valid;
import java.security.PublicKey;

public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {

    public Boolean existsByStdNum(long studentNumber);
    public Boolean existsByEmail(String email);
    public MemberEntity findByEmail(String email);
    public Boolean existsByToken(String token);
    public MemberEntity findByToken(String token);

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.token = :token WHERE m.email = :email")
    public int updateToken(String token, String email);

}
