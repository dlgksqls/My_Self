package hello.my_self.projectstack.controller;

import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import hello.my_self.projectstack.dto.ProjectStackCreateResponse;
import hello.my_self.projectstack.dto.MultiProjectStackResponse;
import hello.my_self.projectstack.dto.SingleProjectStackResponse;
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
    public ResponseEntity<ProjectStackCreateResponse> create(ProjectStackCreateDto createDto) {
        ProjectStack projectStack = projectStackService.create(createDto);
        return new ResponseEntity<>(ProjectStackCreateResponse.response(projectStack), HttpStatus.CREATED);
    }

    @GetMapping("{psId}")
    public ResponseEntity<SingleProjectStackResponse> findById(@PathVariable("psId") Long psId){
        ProjectStack projectStack = projectStackService.findById(psId);
        return new ResponseEntity<>(SingleProjectStackResponse.response(projectStack), HttpStatus.OK);
    }

    @GetMapping("/projects/{pjId}")
    public ResponseEntity<MultiProjectStackResponse> findByProjectId(@PathVariable("pjId") Long pjId) {
        List<ProjectStack> resultList = projectStackService.findByProjectId(pjId);

        if (resultList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        String projectName = resultList.get(0).getProject().getName();

        MultiProjectStackResponse dto = MultiProjectStackResponse.builder()
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

    @DeleteMapping("/ps/{psId}")
    public ResponseEntity<String> deleteByPsId(
            @PathVariable("psId") Long psId
    ) {
        projectStackService.deleteByPsId(psId);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }

    @DeleteMapping("/projects/{pjId}")
    public ResponseEntity<String> deleteByProjectId(
            @PathVariable("pjId") Long pjId
    ){
        projectStackService.deleteByProjectId(pjId);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }

    @DeleteMapping("/projects/{pjId}/stacks/{stId}")
    public ResponseEntity<String> deleteByProjectIdAndStackId(
            @PathVariable("pjId") Long pjId,
            @PathVariable("stId") Long stId
    ){
        projectStackService.deleteByProjectIdAndStackId(pjId, stId);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }
}
