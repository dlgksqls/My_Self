package hello.my_self.myproject.service;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyProjectRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProjectServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;
    private MyProjectService myProjectService;
    private MyProjectRepository myProjectRepository;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository);
    }

    @Test
    public void MyProjecetService_create로_새로운_프로젝트를_생성할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        ProjectCreateDto createProjectDto = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .member(member)
                .build();

        // when
        MyProject project = myProjectService.create(createProjectDto);

        // then
        assertThat(project.getId()).isEqualTo(1L);
        assertThat(project.getName()).isEqualTo("가볼까?");
        assertThat(project.getRole()).isEqualTo("팀장");
        assertThat(project.getDescription()).isEqualTo("졸업작품");
        assertThat(project.getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
        assertThat(project.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyProjecetService_findByName으로_프로젝트를_찾을_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        ProjectCreateDto createProjectDto = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .member(member)
                .build();

        MyProject myProject = myProjectService.create(createProjectDto);

        // when
        MyProject project = myProjectService.findByName(myProject.getName());

        // then
        assertThat(project.getId()).isEqualTo(1L);
        assertThat(project.getName()).isEqualTo("가볼까?");
        assertThat(project.getRole()).isEqualTo("팀장");
        assertThat(project.getDescription()).isEqualTo("졸업작품");
        assertThat(project.getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
        assertThat(project.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyProjecetService_findByName으로_프로젝트를_찾고_update로_프로젝트를_수정할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        ProjectCreateDto createProjectDto = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .member(member)
                .build();

        MyProject myProject = myProjectService.create(createProjectDto);
        MyProject project = myProjectService.findByName(myProject.getName());

        ProjectUpdateDto projectUpdateDto = ProjectUpdateDto.builder()
                .description("좋은 성적을 거뒀습니다.")
                .build();

        // when
        MyProject update = myProjectService.update(project.getName(), projectUpdateDto);

        // then
        assertThat(update.getId()).isEqualTo(1L);
        assertThat(update.getName()).isEqualTo("가볼까?");
        assertThat(update.getRole()).isEqualTo("팀장");
        assertThat(update.getDescription()).isEqualTo("좋은 성적을 거뒀습니다.");
        assertThat(update.getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
        assertThat(update.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyProjecetService_findByName으로_프로젝트를_찾고_delete로_프로젝트를_삭제할_수_있다(){
        // given
        MemberCreateDto createMemberDto = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member member = memberService.create(createMemberDto);

        ProjectCreateDto createProjectDto = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .member(member)
                .build();

        MyProject myProject = myProjectService.create(createProjectDto);
        MyProject project = myProjectService.findByName(myProject.getName());

        // when
        myProjectService.delete(project.getName());

        // then
        assertThatThrownBy(() -> myProjectService.findByName(project.getName()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 프로젝트는 없습니다.");
    }
}
