package hello.my_self.member.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberRelatedDto;
import hello.my_self.member.service.MemberRelatedService;
import hello.my_self.member.service.MemberService;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.MyProjectResponse;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardResponse;
import hello.my_self.myreward.service.MyRewardService;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.MySchoolResponse;
import hello.my_self.myschool.service.MySchoolService;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackResponse;
import hello.my_self.mystack.service.MyStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRelatedServiceImpl implements MemberRelatedService {

    private final MemberService memberService;
    private final MyProjectService myProjectService;
    private final MyRewardService myRewardService;
    private final MySchoolService mySchoolService;
    private final MyStackService myStackService;

    @Override
    public MemberRelatedDto viewAllRelation(Long memberId) {
        Member member = memberService.findById(memberId);
        List<MyProject> projects = myProjectService.findByMember(member.getId());
        List<MyReward> rewards = myRewardService.findByMember(member.getId());
        List<MySchool> schools = mySchoolService.findByMember(member.getId());
        List<MyStack> stacks = myStackService.findByMember(member.getId());

        List<MyProjectResponse> projectResponse = new ArrayList<>();
        List<MyRewardResponse> rewardResponse = new ArrayList<>();
        List<MySchoolResponse> schoolResponses = new ArrayList<>();
        List<MyStackResponse> stackResponses = new ArrayList<>();

        for (MyProject project : projects) {
            projectResponse.add(MyProjectResponse.response(project));
        }

        for (MyReward reward : rewards) {
            rewardResponse.add(MyRewardResponse.response(reward));
        }

        for (MySchool school : schools) {
            schoolResponses.add(MySchoolResponse.response(school));
        }

        for (MyStack stack : stacks) {
            stackResponses.add(MyStackResponse.response(stack));
        }

        return new MemberRelatedDto(member, projectResponse, rewardResponse, schoolResponses, stackResponses);
    }
}
