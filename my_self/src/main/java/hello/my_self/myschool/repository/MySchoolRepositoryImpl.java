package hello.my_self.myschool.repository;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.entity.MySchoolEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySchoolRepositoryImpl implements MySchoolRepository{

    private final MySchoolJpaRepository mySchoolJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MySchool save(MySchool mySchool) {
        MemberEntity getMember = memberJpaRepository.findById(mySchool.getMember().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 학생은 없습니다."));
        return mySchoolJpaRepository.save(MySchoolEntity.toEntity(mySchool, getMember)).toDomain();
    }

    @Override
    public MySchool findById(Long id) {
        return mySchoolJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 학교는 등록되지 않았습니다."))
                .toDomain();
    }

    @Override
    public MySchool update(Long id, SchoolUpdateDto updateDto) {
        MySchool myschool = mySchoolJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 학교는 등록되지 않았습니다."))
                .toDomain();

        myschool.update(updateDto);
        return myschool;
    }

    @Override
    public void delete(Long id) {
        MySchoolEntity mySchoolEntity = mySchoolJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 학교는 등록되지 않았습니다."));

        mySchoolJpaRepository.delete(mySchoolEntity);
    }
}
