package hello.my_self.myschool.service;

import hello.my_self.common.FirstMemberCreate;
import hello.my_self.common.FirstSchoolCreate;
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
import hello.my_self.myschool.service.serviceimpl.MySchoolServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MySchoolServiceTest {

    MemberRepository memberRepository;
    MemberService memberService;
    MySchoolRepository mySchoolRepository;
    MySchoolService mySchoolService;
    Member member;
    SchoolCreateDto createDto;

    @BeforeEach
    void init(){
        memberRepository = new FakeMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);

        mySchoolRepository = new FakeMySchoolRepository();
        mySchoolService = new MySchoolServiceImpl(mySchoolRepository, memberRepository);

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
    public void MySchoolService_create_로_학교를_등록할_수_있다(){
        // given
        // when
        MySchool mySchool = mySchoolService.create(createDto);

        // then
        assertThat(mySchool.getName()).isEqualTo("계명대학교");
        assertThat(mySchool.getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(mySchool.getMajor()).isEqualTo("컴퓨터공학과");
        assertThat(mySchool.getScore()).isEqualTo(4.0);
        assertThat(mySchool.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchoolService_findById_로_학교를_조회할_수_있다(){
        // given
        MySchool mySchool = mySchoolService.create(createDto);

        // when
        MySchool findSchool = mySchoolService.findById(mySchool.getId());

        // then
        assertThat(findSchool.getName()).isEqualTo("계명대학교");
        assertThat(findSchool.getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(findSchool.getMajor()).isEqualTo("컴퓨터공학과");
        assertThat(findSchool.getScore()).isEqualTo(4.0);
        assertThat(findSchool.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchoolService_findById_로_등록되지_않은_학교를_조회하려고_하면_예외를_던진다(){
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            MySchool findSchool = mySchoolService.findById(4L);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void MySchoolService_update_로_등록된_학교를_수정할_수_있다(){
        // given
        MySchool mySchool = mySchoolService.create(createDto);
        SchoolUpdateDto updateDto = SchoolUpdateDto.builder()
                .name("서울대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("조경학과")
                .score(4.5)
                .build();

        // when
        MySchool updateSchool = mySchoolService.update(mySchool.getId(), updateDto);

        // then
        assertThat(updateSchool.getName()).isEqualTo("서울대학교");
        assertThat(updateSchool.getGraduation_date()).isEqualTo(LocalDate.of(2025, 2, 18));
        assertThat(updateSchool.getMajor()).isEqualTo("조경학과");
        assertThat(updateSchool.getScore()).isEqualTo(4.5);
        assertThat(updateSchool.getMember().getName()).isEqualTo("이한빈");
    }

    @Test
    public void MySchoolService_update_에서_등록되지_않은_학교를_수정하려고_하면_예외를_던진다(){
        // given
        SchoolUpdateDto updateDto = SchoolUpdateDto.builder()
                .name("서울대학교")
                .graduation_date(LocalDate.of(2025, 2, 18))
                .major("조경학과")
                .score(4.5)
                .build();
        // when
        // then
        assertThatThrownBy(() -> {
            MySchool findSchool = mySchoolService.update(4L, updateDto);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void MySchoolService_delete_로_학교를_지울_수_있다(){
        // given
        MySchool mySchool = mySchoolService.create(createDto);

        // when
        mySchoolService.delete(mySchool);

        // then
        assertThatThrownBy(() -> {
            mySchoolService.findById(mySchool.getId());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
