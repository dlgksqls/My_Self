package hello.my_self.myschool.controller;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.service.MySchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myschool")
@RequiredArgsConstructor
public class MySchoolController {

    private final MySchoolService mySchoolService;


    public ResponseEntity<MySchool> create(SchoolCreateDto createDto) {
        MySchool mySchool = mySchoolService.create(createDto);
        return new ResponseEntity<>(mySchool, HttpStatus.CREATED);
    }

    public ResponseEntity<MySchool> update(Long id, SchoolUpdateDto updateDto) {
        MySchool mySchool = mySchoolService.update(id, updateDto);
        return new ResponseEntity<>(mySchool, HttpStatus.ACCEPTED);
    }

    public void delete(MySchool mySchool) {
        mySchoolService.delete(mySchool);
    }
}
