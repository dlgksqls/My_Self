package hello.my_self.projectstack.dto;

import hello.my_self.projectstack.domain.ProjectStack;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingleProjectStackResponse {

    private String projectName;
    private String stackName;


    public static SingleProjectStackResponse response(ProjectStack projectStack) {
        return SingleProjectStackResponse.builder()
                .projectName(projectStack.getProject().getName())
                .stackName(projectStack.getStack().getName())
                .build();
    }
}
