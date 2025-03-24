package hello.my_self.myproject.controller;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.MyProjectCreateResponseDto;
import hello.my_self.myproject.dto.MyProjectResponse;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.service.MyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myproject")
public class MyProjectController {

    private final MyProjectService myProjectService;

    @PostMapping("")
    public ResponseEntity<MyProjectCreateResponseDto> create(ProjectCreateDto createProjectDto) {
        MyProject newProject = myProjectService.create(createProjectDto);
        return new ResponseEntity<>(MyProjectCreateResponseDto.response(newProject), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MyProjectResponse> getProjectById(@PathVariable("id") long id) {
        MyProject findProject = myProjectService.findById(id);
        return new ResponseEntity<>(MyProjectResponse.response(findProject), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MyProjectResponse> update(@PathVariable("id") long id, ProjectUpdateDto updateProjectDto) {
        MyProject updatedProject = myProjectService.update(id, updateProjectDto);
        return new ResponseEntity<>(MyProjectResponse.response(updatedProject), HttpStatus.ACCEPTED);
    }
}
