package hello.my_self.member.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.Sex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MemberResponseDto {

    private String name;
    private LocalDate birth;
    private int age;
    private Sex sex;
    private String description;

    public static MemberResponseDto response(Member member){
        return MemberResponseDto.builder()
                .name(member.getName())
                .birth(member.getBirth())
                .age(member.getAge())
                .sex(member.getSex())
                .description(member.getDescription())
                .build();
    }
}
