package hello.my_self.common;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class FirstMemberCreate {

    public static Member createFirstMember(){
        MemberCreateDto member = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member newMember = new Member();
        newMember.create(member);
        newMember.setId(1L);
        return newMember;
    }
}
