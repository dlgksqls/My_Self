package hello.my_self.myschool.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.myschool.dto.SchoolCreateDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
public class MySchool {

    private Long id;
    private String name;
    private String major;
    private double score;
    private LocalDate graduation_date;
    private Member member;

    public MySchool(Long id, String name, String major, double score, LocalDate graduation_date, Member member) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.score = score;
        this.graduation_date = graduation_date;
        this.member = member;
    }

    public MySchool() {
    }

    public void create(SchoolCreateDto createDto) {
        this.name = createDto.getName();
        this.major = createDto.getMajor();
        this.score = createDto.getScore();
        this.graduation_date = createDto.getGraduation_date();
        this.member = createDto.getMember();
    }
}
