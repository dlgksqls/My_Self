package hello.my_self.myreward.controller;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyRewardRepository;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardCreateResponseDto;
import hello.my_self.myreward.dto.MyRewardResponse;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.repository.MyRewardRepository;
import hello.my_self.myreward.service.MyRewardService;
import hello.my_self.myreward.service.serviceimpl.MyRewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MyRewardControllerTest {
    private MemberService memberService;
    private MemberRepository memberRepository;
    private MyRewardController myRewardController;
    private MyRewardService myRewardService;
    private MyRewardRepository myRewardRepository;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myRewardRepository = new FakeMyRewardRepository();
        myRewardService = new MyRewardServiceImpl(myRewardRepository, memberService);
        myRewardController = new MyRewardController(myRewardService);
    }

    @Test
    public void MyRewardController_로_상을_등록할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        MyRewardCreateDto createRewardDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .memberId(member.getId())
                .build();

        // when
        ResponseEntity<MyRewardCreateResponseDto> result = myRewardController.create(createRewardDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("장려상");
        assertThat(result.getBody().getHost()).isEqualTo("계명대학교 산학인재원");
        assertThat(result.getBody().getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(result.getBody().getNumber()).isEqualTo("K-38");
        assertThat(result.getBody().getMemberName()).isEqualTo("이한빈");
    }

    @Test
    public void MyProjectController_로_상을_조회할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        MyRewardCreateDto createRewardDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .memberId(member.getId())
                .build();

        MyReward myReward = myRewardService.create(createRewardDto);

        // when
        ResponseEntity<MyRewardResponse> result = myRewardController.getRewardById(myReward.getName());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getName()).isEqualTo("장려상");
        assertThat(result.getBody().getHost()).isEqualTo("계명대학교 산학인재원");
        assertThat(result.getBody().getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(result.getBody().getNumber()).isEqualTo("K-38");
        assertThat(result.getBody().getMemberName()).isEqualTo("이한빈");
    }

    @Test
    public void MyRewardController_로_상을_수정_할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        MyRewardCreateDto createRewardDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .memberId(member.getId())
                .build();

        MyReward reward = myRewardService.create(createRewardDto);
        MyRewardUpdateDto myRewardUpdateDto = MyRewardUpdateDto.builder()
                .name("대상")
                .description("캡스톤 디자인 대상입니다.")
                .build();

        // when
        ResponseEntity<MyRewardResponse> result = myRewardController.update(reward.getName(), myRewardUpdateDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getName()).isEqualTo("대상");
        assertThat(result.getBody().getHost()).isEqualTo("계명대학교 산학인재원");
        assertThat(result.getBody().getDescription()).isEqualTo("캡스톤 디자인 대상입니다.");
        assertThat(result.getBody().getNumber()).isEqualTo("K-38");
        assertThat(result.getBody().getMemberName()).isEqualTo("이한빈");
    }
}
