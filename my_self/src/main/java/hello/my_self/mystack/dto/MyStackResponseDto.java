package hello.my_self.mystack.dto;

import hello.my_self.mystack.domain.MyStack;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyStackResponseDto {

    private String name;
    private Long memberId;
    private Long projectId;

    public static MyStackResponseDto response(MyStack myStack){
        return MyStackResponseDto.builder()
                .name(myStack.getName())
                .memberId(myStack.getMember().getId())
                .projectId(myStack.getMyProject().getId())
                .build();
    }
}
