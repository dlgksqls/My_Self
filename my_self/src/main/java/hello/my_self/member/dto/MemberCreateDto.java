package hello.my_self.member.dto;

import hello.my_self.member.entity.Sex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MemberCreateDto {

    private String name;
    private LocalDate birth;
    private int age;
    private Sex sex;
    private String description;
}
