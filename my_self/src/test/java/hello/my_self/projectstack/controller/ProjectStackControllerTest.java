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
import hello.my_self.projectstack.dto.ProjectStackCreateResponse;
import hello.my_self.projectstack.dto.MultiProjectStackResponse;
import hello.my_self.projectstack.dto.SingleProjectStackResponse;
import hello.my_self.projectstack.repository.ProjectStackRepository;
import hello.my_self.projectstack.service.ProjectStackService;
import hello.my_self.projectstack.service.serviceimpl.ProjectStackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProjectStackControllerTest {

    Member member;
    MyProject project1;
    MyProject project2;
    MyStack stack1;
    MyStack stack2;

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
        project1 = FirstProjectCreate.createFirstProject(member);
        project2 = FirstProjectCreate.createSecondProject(member);
        stack1 = FirstStackCreate.createFirstStack(member);
        stack2 = FirstStackCreate.createSecondStack(member);

        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myStackRepository = new FakeMyStackRepository();

        projectStackRepository = new FakeProjectStackRepository();
        projectStackService = new ProjectStackServiceImpl(myProjectRepository, myStackRepository, projectStackRepository);
        projectStackController = new ProjectStackController(projectStackService);

        member = memberRepository.save(member);
        project1 = myProjectRepository.save(project1);
        project2 = myProjectRepository.save(project2);
        stack1 = myStackRepository.save(stack1);
        stack2 = myStackRepository.save(stack2);
    }

    @Test
    public void ProjectStackController_로_새로운_데이터를_등록할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();

        // when
        ResponseEntity<ProjectStackCreateResponse> result = projectStackController.create(createDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getId()).isEqualTo(1L);
        assertThat(result.getBody().getProjectName()).isEqualTo("가볼까?");
        assertThat(result.getBody().getStackName()).isEqualTo("Spring");
    }

    @Test
    public void ProjectStackController_findById_로_연관관계를_조회할_수_있다(){
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        ProjectStack ps1 = projectStackService.create(createDto1);
        ProjectStack ps2 = projectStackService.create(createDto2);

        // when
        ResponseEntity<SingleProjectStackResponse> result1 = projectStackController.findById(ps1.getId());
        ResponseEntity<SingleProjectStackResponse> result2 = projectStackController.findById(ps2.getId());

        // then
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getBody().getProjectName()).isEqualTo("가볼까?");
        assertThat(result1.getBody().getStackName()).isEqualTo("Spring");

        assertThat(result2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result2.getBody().getProjectName()).isEqualTo("가볼까?");
        assertThat(result2.getBody().getStackName()).isEqualTo("Django");
    }

    @Test
    public void ProjectStackController_findByProjectId_로_프로젝트와_관련된_모든_연관관계를_조회할_수_있다(){
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        ProjectStack ps1 = projectStackService.create(createDto1);
        ProjectStack ps2 = projectStackService.create(createDto2);

        // when
        ResponseEntity<MultiProjectStackResponse> result = projectStackController.findByProjectId(ps1.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getProjectName()).isEqualTo("가볼까?");
        for (String s : result.getBody().getStackName()) {
            if (s.equals("Spring")) {
                assertThat(s).isEqualTo("Spring");
            }
            else{
                assertThat(s).isEqualTo("Django");
            }
        }
    }

    @Test
    public void ProjectStackController_deleteByPsId_로_연관관계를_삭제할_수_있다(){
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        ProjectStack ps1 = projectStackService.create(createDto1);
        ProjectStack ps2 = projectStackService.create(createDto2);

        // when
        ResponseEntity<String> result = projectStackController.deleteByPsId(ps1.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("삭제 완료");
        assertThatThrownBy(() -> {
            projectStackService.findById(ps1.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void ProjectStackController_deleteByProjectId_로_프로젝트와_관련된_모든_연관관계를_삭제할_수_있다(){
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        ProjectStack ps1 = projectStackService.create(createDto1);
        ProjectStack ps2 = projectStackService.create(createDto2);

        // when
        ResponseEntity<String> result = projectStackController.deleteByProjectId(ps1.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("삭제 완료");
        assertThatThrownBy(() -> {
            projectStackService.findByProjectId(ps1.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void ProjectStackController_deleteByProjectIdAndStackId_로_프로젝트와_관련된_스택_연관관계를_삭제할_수_있다(){
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        projectStackService.create(createDto1);
        projectStackService.create(createDto2);

        // when
        ResponseEntity<String> result = projectStackController.deleteByProjectIdAndStackId(project1.getId(), stack1.getId());
        List<ProjectStack> psByProjectId = projectStackService.findByProjectId(project1.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("삭제 완료");
        for (ProjectStack projectStack : psByProjectId) {
            assertThat(projectStackService.findById(projectStack.getId()).getStack().getName()).isEqualTo("Django");
        }
    }
}
