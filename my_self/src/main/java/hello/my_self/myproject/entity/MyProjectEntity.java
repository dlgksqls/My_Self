package hello.my_self.myproject.entity;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.mystack.entity.MyStackEntity;
import hello.my_self.projectstack.entity.ProjectStackEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class MyProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String description;
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "myProjectEntity", cascade = CascadeType.REMOVE)
    private List<ProjectStackEntity> projectStackEntityList;

    public static MyProjectEntity toEntity(MyProject project, MemberEntity getMember) {
        MyProjectEntity myProjectEntity = new MyProjectEntity();
        myProjectEntity.name = project.getName();
        myProjectEntity.role = project.getRole();
        myProjectEntity.description = project.getDescription();
        myProjectEntity.link = project.getLink();
        myProjectEntity.memberEntity = getMember;
        getMember.getMyProjectEntityList().add(myProjectEntity);
        myProjectEntity.projectStackEntityList = new ArrayList<>();

        return myProjectEntity;
    }


    public MyProject toDomain() {
        return MyProject.builder()
                .id(id)
                .name(name)
                .role(role)
                .description(description)
                .link(link)
                .member(memberEntity.toDomain())
                .build();
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
