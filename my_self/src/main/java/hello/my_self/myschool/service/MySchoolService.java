package hello.my_self.myschool.service;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;

public interface MySchoolService {
    MySchool create(SchoolCreateDto createDto);

    MySchool findById(Long id);

    MySchool update(Long id, SchoolUpdateDto updateDto);

    void delete(MySchool mySchool);
}
