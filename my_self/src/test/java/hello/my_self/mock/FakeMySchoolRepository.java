package hello.my_self.mock;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.repository.MySchoolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMySchoolRepository implements MySchoolRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<MySchool> data = new ArrayList<>();

    @Override
    public MySchool save(MySchool mySchool) {
        MySchool regiSchool = MySchool.builder()
                .id(autoGeneratedId.incrementAndGet())
                .name(mySchool.getName())
                .graduation_date(mySchool.getGraduation_date())
                .major(mySchool.getMajor())
                .score(mySchool.getScore())
                .member(mySchool.getMember())
                .build();

        data.add(regiSchool);
        return regiSchool;
    }

    @Override
    public MySchool findById(Long id) {
        return data.stream()
                .filter(mySchool -> mySchool.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 학교는 등록되지 않았습니다."));
    }

    @Override
    public MySchool update(Long id, SchoolUpdateDto updateDto) {
        MySchool findSchool = data.stream()
                .filter(mySchool -> mySchool.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 학교는 등록되지 않았습니다."));

        findSchool.update(updateDto);
        return findSchool;
    }

    @Override
    public void delete(Long id) {
        data.removeIf(mySchool -> mySchool.getId().equals(id));
    }
}
