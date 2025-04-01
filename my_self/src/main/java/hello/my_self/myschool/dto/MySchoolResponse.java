package hello.my_self.myschool.dto;

import hello.my_self.myschool.domain.MySchool;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MySchoolResponse {

    private String name;
    private String major;
    private double score;
    private LocalDate graduation_date;
    private String memberName;

    public static MySchoolResponse response(MySchool mySchool) {
        return MySchoolResponse.builder()
                .name(mySchool.getName())
                .major(mySchool.getMajor())
                .score(mySchool.getScore())
                .graduation_date(mySchool.getGraduation_date())
                .memberName(mySchool.getMember().getName())
                .build();
    }
}
