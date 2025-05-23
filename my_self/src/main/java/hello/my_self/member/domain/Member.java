package hello.my_self.member.domain;

import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.entity.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
@Setter
public class Member {

    private Long id;
    private String name;
    private LocalDate birth;
    private int age;
    private Sex sex;
    private String description;

    public Member() {
    }

    public Member(Long id, String name, LocalDate birth, int age, Sex sex, String description) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.age = age;
        this.sex = sex;
        this.description = description;
    }

    public void create(MemberCreateDto createDto) {
        this.name = createDto.getName();
        this.birth = createDto.getBirth();
        this.age = createDto.getAge();
        this.sex = createDto.getSex();
        this.description = createDto.getDescription();
    }

    public void update(MemberUpdateDto updateDto) {
        this.name = updateDto.getName();
        this.description = updateDto.getDescription();
    }

    public void createFistMember() {
        this.id = 1L;
        this.name = "lee";
        this.birth = LocalDate.of(1999, 12, 03);
        this.age = 13;
        this.sex = Sex.male;
        this.description = "안녕하세요";
    }
}
