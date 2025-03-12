package hello.my_self.Member.service;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        this.memberRepository = new FakeMemberRepository();
        this.memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    public void UserSevice_create로_유저를_생성할_수_있다(){

        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        Member newMember = memberService.create(createDto);

        // then
        assertThat(newMember.getId()).isEqualTo(1L);
        assertThat(newMember.getName()).isEqualTo("이한빈");
        assertThat(newMember.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(newMember.getAge()).isEqualTo(26);
        assertThat(newMember.getSex()).isEqualTo(Sex.male);
        assertThat(newMember.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }
}
