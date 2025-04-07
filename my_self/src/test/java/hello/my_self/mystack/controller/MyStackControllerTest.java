package hello.my_self.mystack.controller;

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
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackCreateResponseDto;
import hello.my_self.mystack.dto.MyStackResponse;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.mystack.service.MyStackService;
import hello.my_self.mystack.service.serviceimpl.MyStackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStackControllerTest {

    MemberRepository memberRepository;
    MemberService memberService;
    MyProjectRepository myProjectRepository;
    MyProjectService myProjectService;
    MyStackRepository myStackRepository;
    MyStackService myStackService;
    MyStackController myStackController;
    Member member;
    MyProject myProject;
    MyStackCreateDto createDto;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        myProjectRepository = new FakeMyProjectRepository();
        myProjectService = new MyProjectServiceImpl(myProjectRepository, memberService);

        myStackRepository = new FakeMyStackRepository();
        myStackService = new MyStackServiceImpl(myStackRepository, memberRepository);
        myStackController = new MyStackController(myStackService);

        member = FirstMemberCreate.createFirstMember();
        member = memberRepository.save(member);

        myProject = FirstProjectCreate.createFirstProject(member);
        myProject = myProjectRepository.save(myProject);

        createDto = MyStackCreateDto.builder()
                .name("Spring")
                .memberId(member.getId())
                .build();
    }

    @Test
    public void MyStackController_create_로_새로운_stack_를_등록할_수_있다(){
        // given
        // when
        ResponseEntity<MyStackCreateResponseDto>
                result = myStackController.create(createDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("Spring");
        assertThat(result.getBody().getMemberId()).isEqualTo(1L);
    }

    @Test
    public void MyStackController_update_로_stack_를_수정할_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);
        MyStackUpdateDto updateDto = MyStackUpdateDto.builder()
                .name("Django")
                .build();

        // when
        ResponseEntity<MyStackResponse> result =
                myStackController.update(myStack.getId(), updateDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(result.getBody().getName()).isEqualTo("Django");
        assertThat(result.getBody().getMemberId()).isEqualTo(1L);
    }

    @Test
    public void MyStackController_delete_로_stack_를_삭제할_수_있다(){
        // given
        MyStack myStack = myStackService.create(createDto);

        // when
        ResponseEntity<String> result = myStackController.delete(myStack.getId());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(result.getBody()).isEqualTo("삭제 완료");
    }
}
