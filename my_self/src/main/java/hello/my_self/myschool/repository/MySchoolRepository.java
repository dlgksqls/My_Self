package hello.my_self.myschool.repository;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolUpdateDto;

import java.util.List;

public interface MySchoolRepository {
    MySchool save(MySchool mySchool);

    MySchool findById(Long id);

    MySchool update(Long id, SchoolUpdateDto updateDto);

    void delete(Long id);

    List<MySchool> findByMemberId(Long memberId);
}
