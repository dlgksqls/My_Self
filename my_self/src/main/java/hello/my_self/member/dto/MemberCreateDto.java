package hello.my_self.member.dto;

import hello.my_self.member.entity.Sex;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class MemberCreateDto {

    private String name;
    private LocalDate birth;
    private int age;
    private Sex sex;
    private String description;

    public MemberCreateDto(String name, LocalDate birth, int age, Sex sex, String description) {
        this.name = name;
        this.birth = birth;
        this.age = age;
        this.sex = sex;
        this.description = description;
    }
}
