package hello.my_self.mystack.dto;

import hello.my_self.mystack.domain.MyStack;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyStackCreateResponseDto {

    private String name;
    private Long memberId;
    private Long projectId;

    public static MyStackCreateResponseDto response(MyStack myStack){
        return MyStackCreateResponseDto.builder()
                .name(myStack.getName())
                .memberId(myStack.getMember().getId())
                .projectId(myStack.getMyProject().getId())
                .build();
    }
}
