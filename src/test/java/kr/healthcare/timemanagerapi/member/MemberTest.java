package kr.healthcare.timemanagerapi.member;

import kr.healthcare.timemanagerapi.domain.member.Member;
import kr.healthcare.timemanagerapi.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원저장_테스트(){

        memberRepository.save(Member.builder()
                                    .admissionNumber("20100444")
                                    .memberName("홍지훈")
                                    .memberId("wlgnsdldp")
                                    .memberPassword("1234")
                                    .memberGender("M")
                                    .build());

        List<Member> memberList = memberRepository.findAll();

        Member member = memberList.get(0);
        assertThat(member.getAdmissionNumber(),is("20100444"));
        assertThat(member.getMemberName(),is("홍지훈"));
        assertThat(member.getMemberId(),is("wlgnsdldp"));
        assertThat(member.getMemberPassword(),is("1234"));
        assertThat(member.getMemberGender(),is("M"));
    }

    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime now  = LocalDateTime.now();

        memberRepository.save(Member.builder()
                .admissionNumber("20100444")
                .memberName("홍지훈")
                .memberId("wlgnsdldp")
                .memberPassword("1234")
                .memberGender("M")
                .auth("u")
                .build());

        List<Member> memberList = memberRepository.findAll();

        Member member = memberList.get(0);
        assertTrue(member.getCreatedDate().isAfter(now));
        assertTrue(member.getModifiedDate().isAfter(now));
    }

    @Test
    public void 입학번호_중복_테스트(){
        assertThat(memberRepository.countByAdmissionNumber("20100444"),is(1));
    }

    @Test
    public void 아이디_중복_테스트(){
        assertThat(memberRepository.countByMemberId("wlgnsdldp"),is(1));
    }

}
