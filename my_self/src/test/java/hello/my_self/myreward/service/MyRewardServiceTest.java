package hello.my_self.myreward.service;

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
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.repository.MyRewardRepository;
import hello.my_self.myreward.service.serviceimpl.MyRewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyRewardServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;
    private MyRewardRepository myRewardRepository;
    private MyRewardService myRewardService;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myRewardRepository = new FakeMyRewardRepository();
        myRewardService = new MyRewardServiceImpl(myRewardRepository, memberService);
    }

    @Test
    public void MyRewardService_create_로_새로운_상을_등록할_수_있다(){
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
        MyReward reward = myRewardService.create(createRewardDto);

        // then
        assertThat(reward.getId()).isEqualTo(1L);
        assertThat(reward.getName()).isEqualTo("장려상");
        assertThat(reward.getNumber()).isEqualTo("K-38");
        assertThat(reward.getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(reward.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyRewardService_findById_로_상을_찾을_수_있다(){
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

        // when
        MyReward findReward = myRewardService.findById(reward.getId());

        // then
        assertThat(findReward.getId()).isEqualTo(1L);
        assertThat(findReward.getName()).isEqualTo("장려상");
        assertThat(findReward.getNumber()).isEqualTo("K-38");
        assertThat(findReward.getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(findReward.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyRewardService_findByName_로_상을_찾을_수_있다(){
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

        // when
        MyReward findReward = myRewardService.findByName(reward.getName());

        // then
        assertThat(findReward.getId()).isEqualTo(1L);
        assertThat(findReward.getName()).isEqualTo("장려상");
        assertThat(findReward.getNumber()).isEqualTo("K-38");
        assertThat(findReward.getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(findReward.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyRewardService_update_로_상_정보를_수정할_수_있다(){
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
        MyReward updateReward = myRewardService.update(reward.getName(), myRewardUpdateDto);

        // then
        assertThat(updateReward.getId()).isEqualTo(1L);
        assertThat(updateReward.getName()).isEqualTo("대상");
        assertThat(updateReward.getNumber()).isEqualTo("K-38");
        assertThat(updateReward.getDescription()).isEqualTo("캡스톤 디자인 대상입니다.");
        assertThat(updateReward.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyRewardService_update_로_상_정보를_삭제할_수_있다(){
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

        // when
        myRewardService.delete(reward.getName());

        // then
        assertThatThrownBy(() -> myRewardService.findByName(reward.getName()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상은 없습니다.");
    }
}
