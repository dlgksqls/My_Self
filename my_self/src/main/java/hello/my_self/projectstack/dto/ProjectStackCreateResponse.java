package hello.my_self.projectstack.dto;

import hello.my_self.projectstack.domain.ProjectStack;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStackCreateResponse {

    private Long id;
    private String projectName;
    private String stackName;

    public static ProjectStackCreateResponse response(ProjectStack projectStack) {
        return ProjectStackCreateResponse.builder()
                .id(projectStack.getId())
                .projectName(projectStack.getProject().getName())
                .stackName(projectStack.getStack().getName())
                .build();
    }
}
