package hello.my_self.projectstack.service;

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
import hello.my_self.projectstack.repository.ProjectStackRepository;
import hello.my_self.projectstack.service.serviceimpl.ProjectStackServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ProjectStackServiceTest {

    Member member;
    MyProject project1;
    MyProject project2;
    MyStack stack1;
    MyStack stack2;

    private MemberRepository memberRepository;
    private MemberService memberService;
    private MyProjectService myProjectService;
    private MyProjectRepository myProjectRepository;
    private MyStackRepository myStackRepository;
    private ProjectStackService projectStackService;
    private ProjectStackRepository projectStackRepository;

    @BeforeEach
    void init() {
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

        member = memberRepository.save(member);
        project1 = myProjectRepository.save(project1);
        project2 = myProjectRepository.save(project2);
        stack1 = myStackRepository.save(stack1);
        stack2 = myStackRepository.save(stack2);
    }

    @Test
    public void ProjectStackService_create_로_새로운_연관관계를_생성할_수_있다() {
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())

                .build();

        // when
        ProjectStack pj = projectStackService.create(createDto);

        // then
        assertThat(pj.getStack().getName()).isEqualTo(stack1.getName());
        assertThat(pj.getProject().getName()).isEqualTo(project1.getName());
    }

    @Test
    public void ProjectStackService_findById_로_연관관계를_찾을_수_있다() {
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();

        ProjectStack pj = projectStackService.create(createDto);

        // when
        List<ProjectStack> findPj = projectStackService.findByProjectId(pj.getId());


        // then
        for (ProjectStack projectStack : findPj) {
            assertThat(projectStack.getId()).isEqualTo(pj.getId());
            assertThat(projectStack.getProject().getName()).isEqualTo("가볼까?");
            assertThat(projectStack.getStack().getName()).isEqualTo("Spring");
        }
    }

    @Test
    public void ProjectStackService_findByProjectId_로_project_와_관련된_연관관계를_모두_찾을_수_있다(){
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
        List<ProjectStack> psByProjectId = projectStackService.findByProjectId(ps1.getId());

        // then
        assertThat(psByProjectId.size()).isEqualTo(2);
        for (ProjectStack projectStack : psByProjectId) {

            assertThat(projectStack.getProject().getName()).isEqualTo("가볼까?");

            if (projectStack.getStack().getName().equals("Django")) {
                assertThat(projectStack.getStack().getName()).isEqualTo("Django");
            }
            else {
                assertThat(projectStack.getStack().getName()).isEqualTo("Spring");
            }
        }
    }

    @Test
    public void ProjectStackService_delete_로_ps를_지울_수_있다(){
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
        projectStackService.deleteByPsId(ps1.getId());

        // then
        assertThatThrownBy(() -> {
            projectStackService.findById(ps1.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void ProjectStackService_all_delete_로_프로젝트의_연관관계를_모두_지울_수_있다() {
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
        projectStackService.deleteByProjectId(project1.getId());

        // then
        assertThatThrownBy(() -> {
            projectStackService.findByProjectId(project1.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void ProjectStackService_deleteByProjectIdAndStackId_로_기존의_연관관계를_지울_수_있다() {
        // given
        ProjectStackCreateDto createDto1 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack1.getId())
                .build();
        ProjectStackCreateDto createDto2 = ProjectStackCreateDto.builder()
                .projectId(project1.getId())
                .stackId(stack2.getId())
                .build();

        ProjectStack pj1 = projectStackService.create(createDto1);
        ProjectStack pj2 = projectStackService.create(createDto2);

        // when
        log.info("size = {}", projectStackService.findByProjectId(1L).size());
        projectStackService.deleteByProjectIdAndStackId(pj1.getProject().getId(), pj1.getStack().getId());

        // then
        assertThat(projectStackService.findByProjectId(1L).size()).isEqualTo(1);
    }
}
