package hello.my_self.projectstack.domain;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstProjectCreate;
import hello.my_self.common.FirstStackCreate;
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
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectStackTest {

    Member member;
    MyProject project;
    MyStack stack;

    private MemberRepository memberRepository;
    private MemberService memberService;
    private MyProjectService myProjectService;
    private MyProjectRepository myProjectRepository;

    @BeforeEach
    void init(){
        member = FirstMemberCreate.createFirstMember();
        project = FirstProjectCreate.createFirstProject(member);
        stack = FirstStackCreate.createFirstStack(member);

        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        memberRepository.save(member);
        myProjectRepository.save(project);
    }

    @Test
    public void ProjectStack_도메인은_새로운_객체를_생성할_수_있다(){
        // given
        ProjectStackCreateDto createDto = ProjectStackCreateDto.builder()
                .projectId(project.getId())
                .stackId(stack.getId())
                .build();

        ProjectStack projectStack = new ProjectStack();

        // when
        projectStack.create(project, stack);

        // then
        assertThat(projectStack.getProject()).isEqualTo(project);
        assertThat(projectStack.getStack()).isEqualTo(stack);
    }
}
