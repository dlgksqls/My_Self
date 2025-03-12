package hello.my_self.myschool.entity;

import hello.my_self.member.entity.MemberEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MySchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private float score;
    private Date graduation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
}
