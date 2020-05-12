package kr.healthcare.timemanagerapi.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {
}
