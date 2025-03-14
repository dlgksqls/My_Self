package hello.my_self.myproject.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class MyProject {

    private Long id;
    private String name;
    private String role;
    private String description;
    private String link;
    private Member member;

    public MyProject(Long id, String name, String role, String description, String link, Member member) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.description = description;
        this.link = link;
        this.member = member;
    }

    public MyProject() {
    }


    public void create(ProjectCreateDto project) {
        this.name = project.getName();
        this.role = project.getRole();
        this.description = project.getDescription();
        this.link = project.getLink();
        this.member = project.getMember();
    }

    public void update(ProjectUpdateDto updateProject) {
        this.name = updateProject.getName();
        this.role = updateProject.getRole();
        this.description = updateProject.getDescription();
        this.link = updateProject.getLink();
    }
}
