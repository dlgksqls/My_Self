package hello.my_self.Member.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.entity.Sex;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MemberTest {

    @Test
    public void Member_도메인은_memberCreateDto로_새로운_객체를_생성할_수_있다 () {
        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        Member newMember = new Member();
        newMember.createMember(createDto);

        // then
        assertThat(newMember.getId()).isNull();
        assertThat(newMember.getName()).isEqualTo("이한빈");
        assertThat(newMember.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(newMember.getSex()).isEqualTo(Sex.male);
        assertThat(newMember.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void Member_도매인은_MemberUpdateDto로_객체를_수정할_수_있다(){
        // given
        Member newMember = Member.builder()
                .id(1L)
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

        // when
        newMember.update(updateDto);

        // then
        assertThat(newMember.getId()).isNotNull();
        assertThat(newMember.getName()).isEqualTo("류현진");
        assertThat(newMember.getDescription()).isEqualTo("안녕하세요 야구선수입니다.");
    }
}
