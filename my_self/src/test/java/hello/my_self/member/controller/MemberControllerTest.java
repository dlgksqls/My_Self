package hello.my_self.member.controller;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberCreateResponseDto;
import hello.my_self.member.dto.MemberResponseDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberControllerTest {

    private MemberController memberController;
    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);
        memberController = new MemberController(memberService);
    }

    @Test
    public void MemberController_로_등록된_사용자를_조회할_수_있다(){
        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        Member member = memberService.create(createDto);
        ResponseEntity<MemberResponseDto> result = memberController.getMemberById(member.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getName()).isEqualTo("이한빈");
        assertThat(result.getBody().getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(result.getBody().getAge()).isEqualTo(26);
        assertThat(result.getBody().getSex()).isEqualTo(Sex.male);
        assertThat(result.getBody().getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void MemberController_로_사용자를_등록할_수_있다(){
        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        ResponseEntity<MemberCreateResponseDto> result = memberController.create(createDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("이한빈");
    }
}
