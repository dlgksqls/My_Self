package hello.my_self.myreward.dto;


import hello.my_self.myreward.domain.MyReward;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyRewardResponse {

    private String name;
    private String host;
    private String number;
    private String description;
    private String memberName;

    public static MyRewardResponse response(MyReward newReward) {
        return MyRewardResponse.builder()
                .name(newReward.getName())
                .host(newReward.getHost())
                .number(newReward.getNumber())
                .description(newReward.getDescription())
                .memberName(newReward.getMember().getName())
                .build();
    }
}
