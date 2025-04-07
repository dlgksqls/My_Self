package hello.my_self.member.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.MyProjectResponse;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardResponse;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.MySchoolResponse;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberRelatedDto {

    private Member member;
    private List<MyProjectResponse> projects;
    private List<MyRewardResponse> rewards;
    private List<MySchoolResponse> schools;
    private List<MyStackResponse> stacks;

    public MemberRelatedDto(Member member,
                            List<MyProjectResponse> projects,
                            List<MyRewardResponse> rewards,
                            List<MySchoolResponse> schools,
                            List<MyStackResponse> stacks) {
        this.member = member;
        this.projects = projects;
        this.rewards = rewards;
        this.schools = schools;
        this.stacks = stacks;
    }
}
