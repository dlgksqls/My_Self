package hello.my_self.projectstack.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStackResponseDto {

    private Long id;
    private String projectName;
    private String stackName;
}
