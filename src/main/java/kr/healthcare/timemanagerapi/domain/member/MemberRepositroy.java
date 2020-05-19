package kr.healthcare.timemanagerapi.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

public interface MemberRepositroy extends JpaRepository<MemberEntity, String> {

    public Boolean existsByStdNum(String studentNumber);
    public Boolean existsByEmail(String email);
    public MemberEntity findByEmail(String email);
    public Boolean existsByToken(String token);
    public MemberEntity findByToken(String token);
    public MemberEntity findByStdNum(String studentNumber);

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.token = :token WHERE m.email = :email")
    public int updateToken(String token, String email);

    public List<MemberEntity> findAllByAuthOrderByCreatedDateDesc(String auth);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.acceptYn = 'Y' WHERE m.stdNum = :studentNumber")
    public int updateMemberPermissionApprove(String studentNumber);

}
