package hello.my_self.member.service;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        this.memberRepository = new FakeMemberRepository();
        this.memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    public void MemberSevice_create로_유저를_생성할_수_있다(){

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

    @Test
    public void MemberService_findById로_유저를_찾을_수_있다(){
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
        Member findMember = memberService.findById(newMember.getId());

        // then
        assertThat(findMember.getId()).isEqualTo(1L);
        assertThat(findMember.getName()).isEqualTo("이한빈");
        assertThat(findMember.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(findMember.getAge()).isEqualTo(26);
        assertThat(findMember.getSex()).isEqualTo(Sex.male);
        assertThat(findMember.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void MemberService_update로_유저를_수정할_수_있다(){

        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        MemberUpdateDto updateDto = MemberUpdateDto.builder()
                .name("류현진")
                .description("안녕하세요 야구선수입니다.")
                .build();

       Member findMember = memberService.create(createDto);

        // when
        Member updateMember = memberService.update(findMember.getId(), updateDto);

        // then
        assertThat(updateMember.getId()).isEqualTo(1L);
        assertThat(updateMember.getName()).isEqualTo("류현진");
        assertThat(updateMember.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(updateMember.getAge()).isEqualTo(26);
        assertThat(updateMember.getSex()).isEqualTo(Sex.male);
        assertThat(updateMember.getDescription()).isEqualTo("안녕하세요 야구선수입니다.");
    }

    @Test
    public void MemberService_delete로_유저를_삭제할_수_있다(){

        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member findMember = memberService.create(createDto);

        // when
        memberService.delete(findMember);
        // then
        assertThatThrownBy(() -> memberService.findById(1L))
                .isInstanceOf(NoSuchElementException.class) // 기대하는 예외 클래스
                .hasMessage("구성원이 없습니다."); // 예외 메시지 확인 (필요한 경우)
    }
}
