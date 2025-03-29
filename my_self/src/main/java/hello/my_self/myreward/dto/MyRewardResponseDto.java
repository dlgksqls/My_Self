package hello.my_self.myreward.dto;


import hello.my_self.myreward.domain.MyReward;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyRewardResponseDto {

    private String name;
    private String host;
    private String number;
    private String description;
    private String memberName;

    public static MyRewardResponseDto response(MyReward newReward) {
        return MyRewardResponseDto.builder()
                .name(newReward.getName())
                .host(newReward.getHost())
                .number(newReward.getNumber())
                .description(newReward.getDescription())
                .memberName(newReward.getMember().getName())
                .build();
    }
}
