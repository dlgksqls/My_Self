package hello.my_self.myschool.domain;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstSchoolCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class MySchoolTest {

    @Test
    public void MySchool_도메인은_새로운_객체를_생성할_수_있다(){
        // given
        Member firstMember = FirstMemberCreate.createFirstMember();

        SchoolCreateDto createDto = SchoolCreateDto.builder()
                .name("계명대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("컴퓨터공학과")
                .score(4.0)
                .member(firstMember)
                .build();

        MySchool mySchool = new MySchool();

        // when
        mySchool.create(createDto);

        // then
        assertThat(mySchool.getName()).isEqualTo("계명대학교");
        assertThat(mySchool.getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(mySchool.getMajor()).isEqualTo("컴퓨터공학과");
        assertThat(mySchool.getScore()).isEqualTo(4.0);
        assertThat(mySchool.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchool_도메인은_기존의_객체를_수정할_수_있다(){
        // given
        Member firstMember = FirstMemberCreate.createFirstMember();
        MySchool firstSchool = FirstSchoolCreate.createFirstSchool(firstMember);

        // when
        SchoolUpdateDto updateDto = SchoolUpdateDto.builder()
                .name("서울대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("조경학과")
                .score(4.5)
                .build();

        firstSchool.update(updateDto);
        // then
        assertThat(firstSchool.getName()).isEqualTo("서울대학교");
        assertThat(firstSchool.getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(firstSchool.getMajor()).isEqualTo("조경학과");
        assertThat(firstSchool.getScore()).isEqualTo(4.5);
        assertThat(firstSchool.getMember().getName()).isEqualTo("이한빈");
    }
}
