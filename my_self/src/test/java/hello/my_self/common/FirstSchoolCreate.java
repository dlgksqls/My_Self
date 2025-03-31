package hello.my_self.common;

import hello.my_self.member.domain.Member;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class FirstSchoolCreate {

    public static MySchool createFirstSchool(Member member){
        SchoolCreateDto createDto = SchoolCreateDto.builder()
                .name("계명대학교")
                .graduation_date(LocalDate.of(2025, 02, 18))
                .major("컴퓨터공학과")
                .score(4.0)
                .member(member)
                .build();

        MySchool newSchool = new MySchool();
        newSchool.create(createDto);
        return newSchool;
    }
}
