package hello.my_self.mystack.service;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstProjectCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMyProjectRepository;
import hello.my_self.mock.FakeMyStackRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import hello.my_self.myproject.service.serviceimpl.MyProjectServiceImpl;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.mystack.service.serviceimpl.MyStackServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyStackServiceTest {

    MemberRepository memberRepository;
    MemberService memberService;
    MyProjectRepository myProjectRepository;
    MyProjectService myProjectService;
    MyStackRepository myStackRepository;
    MyStackService myStackService;
    Member member;
    MyProject project;
    MyStackCreateDto createDto;


    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myStackRepository = new FakeMyStackRepository();
        myStackService = new MyStackServiceImpl(myStackRepository, memberRepository);

        member = FirstMemberCreate.createFirstMember();
        member = memberRepository.save(member);

        project = FirstProjectCreate.createFirstProject(member);
        project = myProjectRepository.save(project);

        createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(member.getId())
                .build();

    }

    @Test
    public void MyStackService_의_create_로_새로운_스택을_추가할_수_있다(){
        // given
        // when
        MyStack myStack = myStackService.create(createDto);

        // then
        assertThat(myStack.getName()).isEqualTo("Spring");
        assertThat(myStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyStackService_의_create_로_프로젝트가_없더라도_스택을_추가할_수_있다(){
        // given
        MyStackCreateDto createWithOutProjectDto = MyStackCreateDto.builder()
                .name("MySql")
                .memberId(1L)
                .build();

        // when
        MyStack myStack = myStackService.create(createWithOutProjectDto);

        // then
        assertThat(myStack.getName()).isEqualTo("MySql");
        assertThat(myStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyStackService_의_findById_로_스택을_찾을_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);

        // when
        MyStack findStack = myStackService.findById(myStack.getId());

        // then
        assertThat(findStack.getName()).isEqualTo("Spring");
        assertThat(findStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyStackService_의_findByName_으로_스택을_찾을_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);

        // when
        MyStack findStack = myStackService.findByName(myStack.getName());

        // then
        assertThat(findStack.getName()).isEqualTo("Spring");
        assertThat(findStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyStackService_의_findByName_으로_존재하지_않는_스택을_찾는다면_예외를_발생시킨다(){
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            myStackService.findByName("NodeJs");
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void MyStackService_의_update_로_스택을_수정할_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);
        MyStackUpdateDto updateDto = MyStackUpdateDto.builder()
                .name("Django")
                .build();

        // when
        MyStack updateStack = myStackService.update(myStack.getId(), updateDto);

        // then
        assertThat(updateStack.getName()).isEqualTo("Django");
        assertThat(updateStack.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MyStackService_의_delete_로_스택을_삭제할_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);

        // when
        myStackService.delete(myStack.getId());

        // then
        assertThatThrownBy(() -> {
            myStackService.findByName(myStack.getName());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
