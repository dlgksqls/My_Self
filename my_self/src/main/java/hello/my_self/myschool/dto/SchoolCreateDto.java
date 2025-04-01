package hello.my_self.myschool.dto;

import hello.my_self.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
public class SchoolCreateDto {

    private String name;
    private String major;
    private double score;
    private LocalDate graduation_date;
    private Long memberId;
}
