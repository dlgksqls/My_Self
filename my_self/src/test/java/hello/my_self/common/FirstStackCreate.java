package hello.my_self.common;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;

public class FirstStackCreate {

    public static MyStack createFirstStack(Member member) {
        MyStackCreateDto createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(1L)
                .build();

        MyStack newStack = new MyStack();
        newStack.create(createDto, member);
        return newStack;
    }

    public static MyStack createSecondStack(Member member) {
        MyStackCreateDto createDto = MyStackCreateDto.builder()
                .name("Django")
                .memberId(1L)
                .build();

        MyStack newStack = new MyStack();
        newStack.create(createDto, member);
        return newStack;
    }
}
