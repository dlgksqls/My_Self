package hello.my_self.myproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ProjectUpdateDto {

    private String name;
    private String role;
    private String description;
    private String link;

    public ProjectUpdateDto(String name, String role, String description, String link) {
        this.name = name;
        this.role = role;
        this.description = description;
        this.link = link;
    }
}
