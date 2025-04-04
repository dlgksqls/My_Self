package hello.my_self.projectstack.entity;

import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.mystack.entity.MyStackEntity;
import hello.my_self.projectstack.domain.ProjectStack;
import jakarta.persistence.*;

@Entity
public class ProjectStackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private MyProjectEntity myProjectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stack_id")
    private MyStackEntity myStackEntity;

    public static ProjectStackEntity toEntity(ProjectStack projectStack,
                                              MyProjectEntity myProjectEntity,
                                              MyStackEntity myStackEntity) {
        ProjectStackEntity ps = new ProjectStackEntity();
        ps.myProjectEntity = myProjectEntity;
        ps.myStackEntity = myStackEntity;
        myProjectEntity.getProjectStackEntityList().add(ps);
        myStackEntity.getProjectStackEntityList().add(ps);

        return ps;
    }

    public ProjectStack toDomain() {
        return ProjectStack.builder()
                .id(id)
                .stack(myStackEntity.toDomain())
                .project(myProjectEntity.toDomain())
                .build();
    }
}
