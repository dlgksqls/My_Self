package hello.my_self.member.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.mystack.domain.MyStack;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberRelatedDto {

    private Member member;
    private List<MyProject> projects;
    private List<MyReward> rewards;
    private List<MySchool> schools;
    private List<MyStack> stacks;

    public MemberRelatedDto(Member member,
                            List<MyProject> projects,
                            List<MyReward> rewards,
                            List<MySchool> schools,
                            List<MyStack> stacks) {
        this.member = member;
        this.projects = projects;
        this.rewards = rewards;
        this.schools = schools;
        this.stacks = stacks;
    }
}
