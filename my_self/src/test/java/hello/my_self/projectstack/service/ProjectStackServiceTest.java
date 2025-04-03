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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProjectStackServiceTest {

    Member member;
    MyProject project;
    MyStack stack;

    private MemberRepository memberRepository;
    private MemberService memberService;
    private MyProjectService myProjectService;
    private MyProjectRepository myProjectRepository;
    private MyStackRepository myStackRepository;
    private ProjectStackService projectStackService;
    private ProjectStackRepository projectStackRepository;

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

        member = memberRepository.save(member);
        project = myProjectRepository.save(project);
        stack = myStackRepository.save(stack);
    }

    @Test
    public void ProjectStackService_create_로_새로운_연관관계를_생성할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        // when
        ProjectStack pj = projectStackService.create(createDto);

        // then
        assertThat(pj.getStack().getName()).isEqualTo(stack.getName());
        assertThat(pj.getProject().getName()).isEqualTo(project.getName());
    }

    @Test
    public void ProjectStackService_findById_로_연관관계를_찾을_수_있다() {
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        ProjectStack pj = projectStackService.create(createDto);

        // when
        List<ProjectStack> findPj = projectStackService.findById(pj.getId());

        // then
        for (ProjectStack projectStack : findPj) {
            assertThat(projectStack.getId()).isEqualTo(pj.getId());
            assertThat(projectStack.getProject().getName()).isEqualTo("가볼까?");
            assertThat(projectStack.getStack().getName()).isEqualTo("Spring");
        }
    }

    @Test
    public void ProjectStackService_delete_로_기존의_연관관계를_지울_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        ProjectStack pj = projectStackService.create(createDto);

        // when
        projectStackService.allDelete(pj.getId());

        // then
        assertThatThrownBy(() -> {
            projectStackService.findById(pj.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
