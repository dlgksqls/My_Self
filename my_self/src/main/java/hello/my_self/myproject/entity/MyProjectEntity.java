package hello.my_self.myproject.entity;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Optional;

@Entity
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

    public static MyProjectEntity toEntity(MyProject project, MemberEntity getMember) {
        MyProjectEntity myProjectEntity = new MyProjectEntity();
        myProjectEntity.name = project.getName();
        myProjectEntity.role = project.getRole();
        myProjectEntity.description = project.getDescription();
        myProjectEntity.link = project.getLink();
        myProjectEntity.memberEntity = getMember;
        getMember.getMyProjectEntityList().add(myProjectEntity);
//        myProjectEntity.memberEntity.getMyProjectEntityList().add(myProjectEntity);

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
