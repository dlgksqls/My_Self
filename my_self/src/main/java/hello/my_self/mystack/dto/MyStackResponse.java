package hello.my_self.mystack.dto;

import hello.my_self.mystack.domain.MyStack;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyStackResponse {

    private String name;
    private Long memberId;

    public static MyStackResponse response(MyStack myStack){
        return MyStackResponse.builder()
                .name(myStack.getName())
                .memberId(myStack.getMember().getId())
                .build();
    }
}
