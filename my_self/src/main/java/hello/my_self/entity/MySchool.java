package hello.my_self.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MySchool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private float score;
    private Date graduation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
