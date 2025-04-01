package hello.my_self.myschool.controller;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import hello.my_self.member.service.serviceimpl.MemberServiceImpl;
import hello.my_self.mock.FakeMemberRepository;
import hello.my_self.mock.FakeMySchoolRepository;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.repository.MySchoolRepository;
import hello.my_self.myschool.service.MySchoolService;
import hello.my_self.myschool.service.serviceimpl.MySchoolServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MySchoolControllerTest {

    MemberRepository memberRepository;
    MemberService memberService;
    MySchoolRepository mySchoolRepository;
    MySchoolService mySchoolService;
    MySchoolController mySchoolController;
    Member member;
    SchoolCreateDto createDto;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        mySchoolRepository = new FakeMySchoolRepository();
        mySchoolService = new MySchoolServiceImpl(mySchoolRepository, memberRepository);
        mySchoolController = new MySchoolController(mySchoolService);

        member = FirstMemberCreate.createFirstMember();
        member = memberRepository.save(member);
        createDto = SchoolCreateDto.builder()
                .name("계명대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("컴퓨터공학과")
                .score(4.0)
                .memberId(member.getId())
                .build();
    }

    @Test
    public void MySchoolController_로_새로운_학교를_등록할_수_있다(){
        // given
        // when
        ResponseEntity<MySchool> result = mySchoolController.create(createDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("계명대학교");
        assertThat(result.getBody().getScore()).isEqualTo(4.0);
        assertThat(result.getBody().getMajor()).isEqualTo("컴퓨터공학과");
        assertThat(result.getBody().getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(result.getBody().getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchoolController_로_등록된_학교를_수정할_수_있다(){
        // given
        MySchool mySchool = mySchoolService.create(createDto);
        SchoolUpdateDto updateDto = SchoolUpdateDto.builder()
                .name("서울대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("조경학과")
                .score(4.5)
                .build();

        // when
        ResponseEntity<MySchool> result = mySchoolController.update(mySchool.getId(), updateDto);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(result.getBody().getName()).isEqualTo("서울대학교");
        assertThat(result.getBody().getScore()).isEqualTo(4.5);
        assertThat(result.getBody().getMajor()).isEqualTo("조경학과");
        assertThat(result.getBody().getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(result.getBody().getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchoolController_로_등록된_학교를_삭제할_수_있다(){
        // given
        MySchool mySchool = mySchoolService.create(createDto);

        // when
        mySchoolController.delete(mySchool);

        // then
        assertThatThrownBy(() -> {
            mySchoolService.findById(mySchool.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
