package hello.my_self.projectstack.controller;

import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import hello.my_self.projectstack.dto.ProjectStackCreateResponseDto;
import hello.my_self.projectstack.dto.ProjectStackResponseDto;
import hello.my_self.projectstack.service.ProjectStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pj")
@RequiredArgsConstructor
public class ProjectStackController {

    private final ProjectStackService projectStackService;

    @PostMapping("")
    public ResponseEntity<ProjectStackCreateResponseDto> create(ProjectStackCreateDto createDto) {
        ProjectStack projectStack = projectStackService.create(createDto);
        return new ResponseEntity<>(ProjectStackCreateResponseDto.response(projectStack), HttpStatus.CREATED);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<List<ProjectStackResponseDto>> findByProjectId(@PathVariable("projectId") Long projectId) {
        List<ProjectStack> resutList = projectStackService.findByProjectId(projectId);
        List<ProjectStackResponseDto> returnList = new ArrayList<>();
        for (ProjectStack projectStack : resutList) {
            Long psId = projectStack.getId();
            String projectName = projectStack.getProject().getName();
            String stackName = projectStack.getStack().getName();

            returnList.add(
                    ProjectStackResponseDto.builder()
                            .id(psId)
                            .projectName(projectName)
                            .stackName(stackName)
                            .build()
            );
        }

        return new ResponseEntity<>(returnList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("")
    public void allDelete(Long pjId){
        projectStackService.allDelete(pjId);
    }
}
