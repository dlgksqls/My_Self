package hello.my_self.myreward.entity;

import hello.my_self.member.entity.MemberEntity;
import jakarta.persistence.*;

@Entity
public class MyRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String host;
    private String number; // 등록 번호
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
}
