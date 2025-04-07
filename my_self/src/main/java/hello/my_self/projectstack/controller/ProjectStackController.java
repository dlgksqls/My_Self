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
    public ResponseEntity<ProjectStackResponseDto> findByProjectId(@PathVariable("projectId") Long projectId) {
        List<ProjectStack> resultList = projectStackService.findByProjectId(projectId);

        if (resultList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        String projectName = resultList.get(0).getProject().getName();

        ProjectStackResponseDto dto = ProjectStackResponseDto.builder()
                .projectName(projectName)
                .stackName(new ArrayList<>())
                .build();

        for (ProjectStack projectStack : resultList) {
            if (projectStack.getStack() != null) {
                dto.getStackName().add(projectStack.getStack().getName());
            }
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @DeleteMapping("")
    public ResponseEntity<String> deleteByProjectId(Long pjId){
        projectStackService.allDelete(pjId);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }

    @DeleteMapping("/projects/{pjId}/stacks/{stId}")
    public ResponseEntity<String> deleteByProjectStack(
            @PathVariable("pjId") Long pjId,
            @PathVariable("stId") Long stId){
        projectStackService.deleteByProjectIdAndStackId(pjId, stId);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }
}
