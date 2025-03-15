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
        if (updateProject.getName() != null) {
            this.name = updateProject.getName();
        }
        if (updateProject.getRole() != null) {
            this.role = updateProject.getRole();
        }
        if (updateProject.getDescription() != null) {
            this.description = updateProject.getDescription();
        }
        if (updateProject.getLink() != null) {
            this.link = updateProject.getLink();
        }
    }
}
