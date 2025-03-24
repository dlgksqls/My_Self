package hello.my_self.myproject.controller;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyProjectRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.MyProjectCreateResponseDto;
import hello.my_self.myproject.dto.MyProjectResponse;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MyProjectControllerTest {
    private MemberRepository memberRepository;
    private MemberService memberService;

    private MyProjectRepository myProjectRepository;
    private MyProjectService myProjectService;
    private MyProjectController myProjectController;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);
        myProjectController = new MyProjectController(myProjectService);
    }

    @Test
    public void MyProjectController_로_프로젝트를_등록할_수_있다(){
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
                .memberId(member.getId())
                .build();
        // when
        ResponseEntity<MyProjectCreateResponseDto> result = myProjectController.create(createProjectDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("가볼까?");
        assertThat(result.getBody().getRole()).isEqualTo("팀장");
        assertThat(result.getBody().getDescription()).isEqualTo("졸업작품");
        assertThat(result.getBody().getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
    }

    @Test
    public void MyProjectController_로_프로젝트를_조회할_수_있다(){
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
                .memberId(member.getId())
                .build();

        MyProject project = myProjectService.create(createProjectDto);

        // when
        ResponseEntity<MyProjectResponse> result = myProjectController.getProjectById(project.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getId()).isEqualTo(1L);
        assertThat(result.getBody().getName()).isEqualTo("가볼까?");
        assertThat(result.getBody().getRole()).isEqualTo("팀장");
        assertThat(result.getBody().getDescription()).isEqualTo("졸업작품");
        assertThat(result.getBody().getLink()).isEqualTo("https://github.com/190000you/BE_AI_GO");
    }

    @Test
    public void MyProjectController_로_프로젝트를_수정할_수_있다(){
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
                .memberId(member.getId())
                .build();

        MyProject project = myProjectService.create(createProjectDto);

        ProjectUpdateDto updateProjectDto = ProjectUpdateDto.builder()
                .name("쇼핑몰")
                .description("개인프로젝트")
                .link("https://github.com/dlgksqls/web_shopping.git")
                .build();

        // when
        ResponseEntity<MyProjectResponse> result = myProjectController.update(project.getId(), updateProjectDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(result.getBody().getId()).isEqualTo(1L);
        assertThat(result.getBody().getName()).isEqualTo("쇼핑몰");
        assertThat(result.getBody().getRole()).isEqualTo("팀장");
        assertThat(result.getBody().getDescription()).isEqualTo("개인프로젝트");
        assertThat(result.getBody().getLink()).isEqualTo("https://github.com/dlgksqls/web_shopping.git");
    }
}
