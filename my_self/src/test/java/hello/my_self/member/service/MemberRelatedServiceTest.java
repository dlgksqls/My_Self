package hello.my_self.member.service;

import hello.my_self.common.*;
import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberRelatedDto;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.serviceimpl.MemberRelatedServiceImpl;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.*;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.MyProjectResponse;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardResponse;
import hello.my_self.myreward.repository.MyRewardRepository;
import hello.my_self.myreward.service.MyRewardService;
import hello.my_self.myreward.service.serviceimpl.MyRewardServiceImpl;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.MySchoolResponse;
import hello.my_self.myschool.repository.MySchoolRepository;
import hello.my_self.myschool.service.MySchoolService;
import hello.my_self.myschool.service.serviceimpl.MySchoolServiceImpl;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackResponse;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.mystack.service.MyStackService;
import hello.my_self.mystack.service.serviceimpl.MyStackServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MemberRelatedServiceTest {

    MemberRepository memberRepository;
    MyProjectRepository myProjectRepository;
    MyRewardRepository myRewardRepository;
    MySchoolRepository mySchoolRepository;
    MyStackRepository myStackRepository;

    MemberService memberService;
    MemberRelatedService memberRelatedService;
    MyProjectService myProjectService;
    MyRewardService myRewardService;
    MySchoolService mySchoolService;
    MyStackService myStackService;
    Member member = FirstMemberCreate.createFirstMember();
    MyProject project = FirstProjectCreate.createFirstProject(member);
    MyReward reward = FirstRewardCreate.createFirstReward(member);
    MySchool school = FirstSchoolCreate.createFirstSchool(member);
    MyStack stack = FirstStackCreate.createFirstStack(member);

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myRewardRepository = new FakeMyRewardRepository();
        myRewardService = new MyRewardServiceImpl(myRewardRepository, memberService);

        mySchoolRepository = new FakeMySchoolRepository();
        mySchoolService = new MySchoolServiceImpl(mySchoolRepository, memberRepository);

        myStackRepository = new FakeMyStackRepository();
        myStackService = new MyStackServiceImpl(myStackRepository, memberRepository);

        memberRelatedService = new MemberRelatedServiceImpl(
                memberService, myProjectService, myRewardService, mySchoolService, myStackService
        );

        member = memberRepository.save(member);
        project = myProjectRepository.save(project);
        reward = myRewardRepository.save(reward);
        school = mySchoolRepository.save(school);
        stack = myStackRepository.save(stack);
    }

    @Test
    public void MemberRelatedServiceImpl_로_연관된_객체를_조회할_수_있다(){
        // given
        // when
        MemberRelatedDto memberRelatedDto = memberRelatedService.viewAllRelation(member.getId());

        // then
        for (MyProjectResponse memberRelatedDtoProject : memberRelatedDto.getProjects()) {
            log.info(memberRelatedDtoProject.getName());
        }
        for (MyRewardResponse memberRelatedDtoReward : memberRelatedDto.getRewards()) {
            log.info(memberRelatedDtoReward.getName());
        }
        for (MySchoolResponse memberRelatedDtoSchool : memberRelatedDto.getSchools()) {
            log.info(memberRelatedDtoSchool.getName());
        }
        for (MyStackResponse memberRelatedDtoStack : memberRelatedDto.getStacks()) {
            log.info(memberRelatedDtoStack.getName());
        }

        assertThat(memberRelatedDto.getMember()).isEqualTo(member);

        for (MyProjectResponse project : memberRelatedDto.getProjects()) {
            assertThat(project.getName()).isEqualTo("가볼까?");
        }

        for (MyRewardResponse reward : memberRelatedDto.getRewards()) {
            assertThat(reward.getName()).isEqualTo("장려상");
        }

        for (MySchoolResponse school : memberRelatedDto.getSchools()) {
            assertThat(school.getName()).isEqualTo("계명대학교");
        }

        for (MyStackResponse stack : memberRelatedDto.getStacks()) {
            assertThat(stack.getName()).isEqualTo("Spring");
        }
    }
}
