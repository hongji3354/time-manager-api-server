package kr.healthcare.timemanagerapi.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

    int countByAdmissionNumber(String admissionNumber);
    int countByMemberId(String memberId);
}
