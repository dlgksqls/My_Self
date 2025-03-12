package hello.my_self.member.entity;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.myreward.entity.MyRewardEntity;
import hello.my_self.myschool.entity.MySchoolEntity;
import hello.my_self.mystack.entity.MyStackEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birth;
    private int age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String description;

    @OneToMany(mappedBy = "memberEntity")
    private ArrayList<MySchoolEntity> mySchoolEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private ArrayList<MyProjectEntity> myProjectEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private ArrayList<MyRewardEntity> myRewardEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private ArrayList<MyStackEntity> myStackEntityList;

    public static MemberEntity be(Member member) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = member.getId();
        memberEntity.name = member.getName();
        memberEntity.age = member.getAge();
        memberEntity.sex = member.getSex();
        memberEntity.description = member.getDescription();

        memberEntity.myProjectEntityList = new ArrayList<>();
        memberEntity.myRewardEntityList = new ArrayList<>();
        memberEntity.mySchoolEntityList = new ArrayList<>();
        memberEntity.myStackEntityList = new ArrayList<>();

        return memberEntity;
    }

    public Member toDomain() {
        return Member.builder()
                .id(id)
                .name(name)
                .age(age)
                .sex(sex)
                .description(description)
                .build();
    }
}
