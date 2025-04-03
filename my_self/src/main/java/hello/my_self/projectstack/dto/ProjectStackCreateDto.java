package hello.my_self.projectstack.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStackCreateDto {

    private Long projectId;
    private Long stackId;
}
