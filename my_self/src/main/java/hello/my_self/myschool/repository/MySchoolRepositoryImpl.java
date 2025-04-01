package hello.my_self.myschool.repository;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MySchoolRepositoryImpl implements MySchoolRepository{

    private final MySchoolJpaRepository mySchoolJpaRepository;

    @Override
    public MySchool save(MySchool mySchool) {
        return mySchoolJpaRepository.save(MySchool.);
    }

    @Override
    public MySchool findById(Long id) {
        return null;
    }

    @Override
    public MySchool update(Long id, SchoolUpdateDto updateDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
