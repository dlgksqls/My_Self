package hello.my_self.myschool.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class MySchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private double score;
    private LocalDate graduation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static MySchoolEntity toEntity(MySchool mySchool, MemberEntity getMember) {
        MySchoolEntity mySchoolEntity = new MySchoolEntity();
        mySchoolEntity.name = mySchool.getName();
        mySchoolEntity.major = mySchool.getMajor();
        mySchoolEntity.score = mySchool.getScore();
        mySchoolEntity.graduation_date = mySchool.getGraduation_date();
        mySchoolEntity.memberEntity = getMember;
        getMember.getMySchoolEntityList().add(mySchoolEntity);

        return mySchoolEntity;
    }

    public MySchool toDomain() {
        return MySchool.builder()
                .id(id)
                .name(name)
                .major(major)
                .score(score)
                .graduation_date(graduation_date)
                .member(memberEntity.toDomain())
                .build();
    }

    public void update(SchoolUpdateDto updateDto) {
        if (updateDto.getName() != null){
            this.name = updateDto.getName();
        }
        if (updateDto.getMajor() != null){
            this.major = updateDto.getMajor();
        }
        if (updateDto.getScore() != 0.0){
            this.score = updateDto.getScore();
        }
        if (updateDto.getGraduation_date() != null){
            this.graduation_date = updateDto.getGraduation_date();
        }
    }
}
