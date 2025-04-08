package hello.my_self.projectstack.dto;

import hello.my_self.projectstack.domain.ProjectStack;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MultiProjectStackResponse {

    private String projectName;
    private List<String> stackName;

    public static MultiProjectStackResponse response(ProjectStack projectStack) {
        return MultiProjectStackResponse.builder()
                .projectName(projectStack.getStack().getName())
                .stackName(List.of(projectStack.getStack().getName()))
                .build();
    }
}
