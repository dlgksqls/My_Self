package hello.my_self.myproject.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.domain.MyProject;
import jakarta.persistence.*;
import lombok.Builder;

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

    public static MyProjectEntity toEntity(MyProject project) {
        MyProjectEntity myProjectEntity = new MyProjectEntity();
        myProjectEntity.name = project.getName();
        myProjectEntity.role = project.getName();
        myProjectEntity.description = project.getDescription();
        myProjectEntity.link = project.getLink();
        myProjectEntity.memberEntity = MemberEntity.toEntity(project.getMember());
        myProjectEntity.memberEntity.getMyProjectEntityList().add(myProjectEntity);

        return myProjectEntity;
    }


    public MyProject toDomain() {
        return MyProject.builder()
                .name(name)
                .role(role)
                .description(description)
                .link(link)
                .member(memberEntity.toDomain())
                .build();
    }
}
