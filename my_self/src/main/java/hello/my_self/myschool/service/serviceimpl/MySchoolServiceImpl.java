package hello.my_self.myschool.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.repository.MySchoolRepository;
import hello.my_self.myschool.service.MySchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MySchoolServiceImpl implements MySchoolService {

    private final MySchoolRepository mySchoolRepository;
    private final MemberRepository memberRepository;

    @Override
    public MySchool create(SchoolCreateDto createDto) {
        Member member = memberRepository.findById(createDto.getMemberId());
        MySchool mySchool = new MySchool();
        mySchool.create(createDto, member);
        return mySchoolRepository.save(mySchool);
    }

    @Override
    public MySchool findById(Long id) {
        return mySchoolRepository.findById(id);
    }

    @Override
    public MySchool update(Long id, SchoolUpdateDto updateDto) {
        MySchool findSchool = mySchoolRepository.findById(id);
        return mySchoolRepository.update(findSchool.getId(), updateDto);
    }

    @Override
    public void delete(Long id) {
        mySchoolRepository.delete(id);
    }

    @Override
    public List<MySchool> findByMember(Long memberId) {
        return mySchoolRepository.findByMemberId(memberId);
    }
}
