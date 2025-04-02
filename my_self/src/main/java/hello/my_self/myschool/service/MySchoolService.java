package hello.my_self.myschool.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;

import java.util.List;

public interface MySchoolService {
    MySchool create(SchoolCreateDto createDto);

    MySchool findById(Long id);

    MySchool update(Long id, SchoolUpdateDto updateDto);

    void delete(Long id);
    List<MySchool> findByMember(Long memberId);
}
