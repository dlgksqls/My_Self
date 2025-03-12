package hello.my_self.myproject.entity;

import hello.my_self.member.entity.MemberEntity;
import jakarta.persistence.*;

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
}
