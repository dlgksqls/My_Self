package hello.my_self.mystack.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyStack {

    private Long id;
    private String name;
    private Member member;

    public MyStack() {
    }

    public MyStack(Long id, String name, Member member) {
        this.id = id;
        this.name = name;
        this.member = member;
    }

    public void create(MyStackCreateDto createDto, Member member) {
        this.name = createDto.getName();
        this.member = member;
    }

    public void update(MyStackUpdateDto updateDto) {
        if (updateDto.getName() != null) this.name = updateDto.getName();
    }

    public void createFirstStack(Member member) {
        this.name = "Spring";
        this.member = member;
    }
}