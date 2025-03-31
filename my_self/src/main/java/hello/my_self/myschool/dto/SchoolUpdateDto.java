package hello.my_self.myschool.dto;

import hello.my_self.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SchoolUpdateDto {

    private String name;
    private String major;
    private double score;
    private LocalDate graduation_date;
}
