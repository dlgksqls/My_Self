package hello.my_self.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date birth;
    private int age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String description;

    @OneToMany(mappedBy = "member")
    private ArrayList<MySchool> mySchoolList;

    @OneToMany(mappedBy = "member")
    private ArrayList<MyProject> myProjectList;

    @OneToMany(mappedBy = "member")
    private ArrayList<MyReward> myRewardList;

    @OneToMany(mappedBy = "member")
    private ArrayList<MyStack> myStackList;
}
