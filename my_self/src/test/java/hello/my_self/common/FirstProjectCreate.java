package hello.my_self.common;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;

import java.time.LocalDate;

public class FirstProjectCreate {

    public static MyProject createFirstProject(Member member){
        ProjectCreateDto createDto = ProjectCreateDto.builder()
                .name("가볼까?")
                .role("팀장")
                .description("졸업작품")
                .link("https://github.com/190000you/BE_AI_GO")
                .memberId(member.getId())
                .build();

        MyProject newProject = new MyProject();
        newProject.create(createDto, member);
        return newProject;
    }
}
