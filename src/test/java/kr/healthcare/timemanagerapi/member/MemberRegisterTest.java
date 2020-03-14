package kr.healthcare.timemanagerapi.member;

import kr.healthcare.timemanagerapi.domain.member.Member;
import kr.healthcare.timemanagerapi.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberRegisterTest {

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
}
