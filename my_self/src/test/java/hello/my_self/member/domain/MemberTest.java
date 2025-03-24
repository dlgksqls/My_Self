package hello.my_self.member.domain;

import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.entity.Sex;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MemberTest {

    @Test
    public void MemberEntity의_toEntity매서드를_통해_Domain을_Entity로_변환할_수_있다(){
        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        Member member = new Member();
        member.create(createDto);
        MemberEntity entity = MemberEntity.toEntity(member);

        // then
        assertThat(entity.getId()).isNull();
        assertThat(entity.getName()).isEqualTo("이한빈");
        assertThat(entity.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(entity.getSex()).isEqualTo(Sex.male);
        assertThat(entity.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void MemberEntity의_toDomain매서드를_통해_Entity를_Domain으로_변환할_수_있다(){
        // given
        MemberCreateDto createDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        // when
        Member member = new Member();
        member.create(createDto);
        MemberEntity entity = MemberEntity.toEntity(member);

        Member domain = entity.toDomain();

        // then
        assertThat(domain.getId()).isNull();
        assertThat(domain.getName()).isEqualTo("이한빈");
        assertThat(domain.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(domain.getSex()).isEqualTo(Sex.male);
        assertThat(domain.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void Member_도메인은_memberCreateDto_로_새로운_객체를_생성할_수_있다 () {
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
        newMember.create(createDto);

        // then
        assertThat(newMember.getId()).isNull();
        assertThat(newMember.getName()).isEqualTo("이한빈");
        assertThat(newMember.getBirth()).isEqualTo(LocalDate.of(2000, 10, 6));
        assertThat(newMember.getSex()).isEqualTo(Sex.male);
        assertThat(newMember.getDescription()).isEqualTo("안녕하세요 개발자입니다.");
    }

    @Test
    public void Member_도매인은_MemberUpdateDto_로_객체를_수정할_수_있다(){
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
