package hello.my_self.myproject.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjectCreateDto {

    private String name;
    private String role;
    private String description;
    private String link;
    private Member member;
}
