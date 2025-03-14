package hello.my_self.myproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectUpdateDto {

    private String name;
    private String role;
    private String description;
    private String link;
}
