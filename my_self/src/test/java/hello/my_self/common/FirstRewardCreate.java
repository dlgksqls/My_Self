package hello.my_self.common;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;

import java.time.LocalDate;

public class FirstRewardCreate {

    public static MyReward createFirstReward(Member member){
        MyRewardCreateDto createDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .build();

        MyReward myReward = new MyReward();
        myReward.create(createDto, member);
        return myReward;
    }
}
