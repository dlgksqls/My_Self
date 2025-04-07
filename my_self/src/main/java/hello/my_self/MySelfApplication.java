package hello.my_self;

import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.repository.MyRewardRepository;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.repository.MySchoolRepository;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.repository.ProjectStackRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MySelfApplication {

    private final MemberRepository memberRepository;
    private final MyProjectRepository myProjectRepository;
    private final MyRewardRepository myRewardRepository;
    private final MySchoolRepository mySchoolRepository;
    private final MyStackRepository myStackRepository;
    private final ProjectStackRepository projectStackRepository;
    public static void main(String[] args) {
        SpringApplication.run(MySelfApplication.class, args);
    }

    @PostConstruct
    void init(){
        Member member = new Member();
        member.createFistMember();
        memberRepository.save(member);

//        MyProject myProject = new MyProject();
//        myProject.createFirstProject(member);
//        myProjectRepository.save(myProject);
//
//        MyReward myReward = new MyReward();
//        myReward.createFirstReward(member);
//        myRewardRepository.save(myReward);
//
//        MySchool mySchool = new MySchool();
//        mySchool.createFirstSchool(member);
//        mySchoolRepository.save(mySchool);
//
//        MyStack myStack = new MyStack();
//        myStack.createFirstStack(member);
//        myStackRepository.save(myStack);
//
//        ProjectStack projectStack = new ProjectStack();
//        projectStack.createFirstProjectStack(myProject, myStack);
//        projectStackRepository.save(projectStack);
    }
}
