package hello.my_self.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberUpdateDto {

    private String name;
    private String description;
}
