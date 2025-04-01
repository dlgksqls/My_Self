package hello.my_self.mystack.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyStackCreateDto {

    private String name;
    private Long memberId;
    private Long projectId;
}
