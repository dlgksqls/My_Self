package hello.my_self.mystack.service;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstProjectCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyProjectRepository;
import hello.my_self.mock.FakeMyStackRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.mystack.service.serviceimpl.MyStackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MyStackServiceTest {

    MemberRepository memberRepository;
    MemberService memberService;
    MyProjectRepository myProjectRepository;
    MyProjectService myProjectService;
    MyStackRepository myStackRepository;
    MyStackService myStackService;
    Member member;
    MyProject project;
    MyStackCreateDto createDto;


    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myStackRepository = new FakeMyStackRepository();
        myStackService = new MyStackServiceImpl();

        member = FirstMemberCreate.createFirstMember();
        member = memberRepository.save(member);

        project = FirstProjectCreate.createFirstProject(member);
        project = myProjectRepository.save(project);

        createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(member.getId())
                .projectId(project.getId())
                .build();
    }

    @Test
    public void MyStackService_의_create_로_새로운_스택을_추가할_수_있다(){
        // given


        // when

        // then
    }
}
