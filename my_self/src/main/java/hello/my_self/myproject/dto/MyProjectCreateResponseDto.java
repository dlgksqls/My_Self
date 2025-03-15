package hello.my_self.myproject.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateResponseDto;
import hello.my_self.myproject.domain.MyProject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyProjectCreateResponseDto {

    private String name;
    private String role;
    private String description;
    private String link;

    public static MyProjectCreateResponseDto response(MyProject newProject) {
        return MyProjectCreateResponseDto.builder()
                .name(newProject.getName())
                .role(newProject.getRole())
                .description(newProject.getDescription())
                .link(newProject.getLink())
                .build();
    }
}
