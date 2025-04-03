package hello.my_self.projectstack.entity;

import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.mystack.entity.MyStackEntity;
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
}
