package hello.my_self.myschool.controller;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.dto.MySchoolCreateResponse;
import hello.my_self.myschool.dto.MySchoolResponse;
import hello.my_self.myschool.dto.SchoolCreateDto;
import hello.my_self.myschool.dto.SchoolUpdateDto;
import hello.my_self.myschool.service.MySchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myschool")
@RequiredArgsConstructor
public class MySchoolController {

    private final MySchoolService mySchoolService;


    @PostMapping("")
    public ResponseEntity<MySchoolCreateResponse> create(SchoolCreateDto createDto) {
        MySchool mySchool = mySchoolService.create(createDto);
        return new ResponseEntity<>(MySchoolCreateResponse.response(mySchool), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MySchoolResponse> update(@PathVariable("id") Long id, SchoolUpdateDto updateDto) {
        MySchool mySchool = mySchoolService.update(id, updateDto);
        return new ResponseEntity<>(MySchoolResponse.response(mySchool), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        mySchoolService.delete(id);
    }
}
