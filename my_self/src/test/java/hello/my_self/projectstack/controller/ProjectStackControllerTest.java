package hello.my_self.projectstack.controller;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstProjectCreate;
import hello.my_self.common.FirstStackCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyProjectRepository;
import hello.my_self.mock.FakeMyStackRepository;
import hello.my_self.mock.FakeProjectStackRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import hello.my_self.projectstack.dto.ProjectStackCreateResponseDto;
import hello.my_self.projectstack.dto.ProjectStackResponseDto;
import hello.my_self.projectstack.repository.ProjectStackRepository;
import hello.my_self.projectstack.service.ProjectStackService;
import hello.my_self.projectstack.service.serviceimpl.ProjectStackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProjectStackControllerTest {

    Member member;
    MyProject project;
    MyStack stack;

    MemberRepository memberRepository;
    MemberService memberService;
    MyProjectService myProjectService;
    MyProjectRepository myProjectRepository;
    MyStackRepository myStackRepository;
    ProjectStackService projectStackService;
    ProjectStackRepository projectStackRepository;
    ProjectStackController projectStackController;


    @BeforeEach
    void init(){
        member = FirstMemberCreate.createFirstMember();
        project = FirstProjectCreate.createFirstProject(member);
        stack = FirstStackCreate.createFirstStack(member);

        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myStackRepository = new FakeMyStackRepository();

        projectStackRepository = new FakeProjectStackRepository();
        projectStackService = new ProjectStackServiceImpl(myProjectRepository, myStackRepository, projectStackRepository);
        projectStackController = new ProjectStackController(projectStackService);

        member = memberRepository.save(member);
        project = myProjectRepository.save(project);
        stack = myStackRepository.save(stack);
    }

    @Test
    public void ProjectStackController_로_새로운_데이터를_등록할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        // when
        ResponseEntity<ProjectStackCreateResponseDto> result = projectStackController.create(createDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getId()).isEqualTo(1L);
        assertThat(result.getBody().getProjectName()).isEqualTo("가볼까?");
        assertThat(result.getBody().getStackName()).isEqualTo("Spring");
    }

    @Test
    public void ProjectStackController_로_연관관계를_조회할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();
        ProjectStack projectStack = projectStackService.create(createDto);

        // when
        ResponseEntity<ProjectStackResponseDto> result = projectStackController.findByProjectId(projectStack.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getProjectName()).isEqualTo("가볼까?");

        for (String s : result.getBody().getStackName()) {
            assertThat(s).isEqualTo("Spring");
        }
    }

    @Test
    public void ProjectStackController_로_기존의_데이터를_삭제할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        ProjectStack projectStack = projectStackService.create(createDto);

        // when
        projectStackController.deleteByProjectId(projectStack.getId());

        // then
        assertThatThrownBy(() -> {
            projectStackService.findByProjectId(projectStack.getId());
            projectStackService.findById(projectStack.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
