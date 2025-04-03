package hello.my_self.mystack.domain;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstProjectCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStackTest {

    @Test
    public void myStack_도메인은_새로운_객체를_생성할_수_있다(){
        // given
        Member firstMember = FirstMemberCreate.createFirstMember();
        MyProject myProject = FirstProjectCreate.createFirstProject(firstMember);

        MyStack myStack = new MyStack();
        MyStackCreateDto createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(1L)
                .build();

        // when
        myStack.create(createDto, firstMember);

        // then
        assertThat(myStack.getName()).isEqualTo("Spring");
        assertThat(myStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void myStack_도메인은_객체를_수정할_수_있다(){
        // given
        Member firstMember = FirstMemberCreate.createFirstMember();
        MyProject myProject = FirstProjectCreate.createFirstProject(firstMember);

        MyStack myStack = new MyStack();
        MyStackCreateDto createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(1L)
                .build();

        MyStackUpdateDto updateDto = MyStackUpdateDto.builder()
                .name("Django")
                .build();

        myStack.create(createDto, firstMember);
        // when
        myStack.update(updateDto);

        // then
        assertThat(myStack.getName()).isEqualTo("Django");
        assertThat(myStack.getMember().getName()).isEqualTo("이한빈");
    }
}
