package hello.my_self.myproject.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.myproject.domain.MyProject;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MyProjectResponse {

    private Long id;
    private String name;
    private String role;
    private String description;
    private String link;
    private String memberName;

    public static MyProjectResponse response(MyProject findProject) {
        return MyProjectResponse.builder()
                .id(findProject.getId())
                .name(findProject.getName())
                .role(findProject.getRole())
                .description(findProject.getDescription())
                .link(findProject.getLink())
                .memberName(findProject.getMember().getName())
                .build();
    }
}
