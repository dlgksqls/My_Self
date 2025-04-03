package hello.my_self.projectstack.dto;

import hello.my_self.projectstack.domain.ProjectStack;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ProjectStackCreateResponseDto {

    private Long id;
    private String projectName;
    private String stackName;

    public static ProjectStackCreateResponseDto response(ProjectStack projectStack) {
        return ProjectStackCreateResponseDto.builder()
                .id(projectStack.getId())
                .projectName(projectStack.getProject().getName())
                .stackName(projectStack.getStack().getName())
                .build();
    }
}
