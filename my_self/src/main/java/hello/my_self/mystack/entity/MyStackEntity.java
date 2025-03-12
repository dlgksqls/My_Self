package hello.my_self.mystack.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.entity.MyProjectEntity;
import jakarta.persistence.*;

@Entity
public class MyStackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private MyProjectEntity myProjectEntity;
}
