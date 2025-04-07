package hello.my_self.projectstack.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ProjectStackResponseDto {

    private String projectName;
    private List<String> stackName;
}
