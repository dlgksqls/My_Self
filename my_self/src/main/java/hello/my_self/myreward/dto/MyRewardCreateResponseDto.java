package hello.my_self.myreward.dto;

import hello.my_self.member.domain.Member;
import hello.my_self.myreward.domain.MyReward;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class MyRewardCreateResponseDto {

    private String name;
    private String host;
    private String number;
    private String description;
    private String memberName;
    public static MyRewardCreateResponseDto response(MyReward newReward) {
        return MyRewardCreateResponseDto.builder()
                .name(newReward.getName())
                .host(newReward.getHost())
                .number(newReward.getNumber())
                .description(newReward.getDescription())
                .memberName(newReward.getMember().getName())
                .build();
    }
}
