package hello.my_self.myproject.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {

    @Test
    public void Project_도메인은_projectCreateDto로_새로운_객체를_생성할_수_있다(){
        // given
        MemberCreateDto member = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member newMember = new Member();
        newMember.create(member);

        ProjectCreateDto project = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .memberId(newMember.getId())
                .build();

        // when
        MyProject newProject = new MyProject();
        newProject.create(project, newMember);

        // then
        assertThat(newProject.getName()).isEqualTo("가볼까?");
        assertThat(newProject.getRole()).isEqualTo("팀장");
        assertThat(newProject.getDescription()).isEqualTo("졸업작품");
        assertThat(newProject.getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
        assertThat(newProject.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void Project_도메인은_projectUpdateDto_로_새로운_객체를_수정할_수_있다(){
        // given
        MemberCreateDto member = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member newMember = new Member();
        newMember.create(member);

        ProjectCreateDto project = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .memberId(newMember.getId())
                .build();

        MyProject newProject = new MyProject();
        newProject.create(project, newMember);

        ProjectUpdateDto updateProject = ProjectUpdateDto.builder()
                .name("쇼핑몰")
                .role("사장")
                .description("개인프로젝트")
                .link("https://github.com/190000you/BE_AI_GO")
                .build();
        // when
        newProject.update(updateProject);

        // then
        assertThat(newProject.getName()).isEqualTo("쇼핑몰");
        assertThat(newProject.getRole()).isEqualTo("사장");
        assertThat(newProject.getDescription()).isEqualTo("개인프로젝트");
        assertThat(newProject.getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
        assertThat(newProject.getMember().getName()).isEqualTo("이한빈");
    }
}
