package hello.my_self.myreward.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyRewardUpdateDto {
    private String name;
    private String host;
    private String number;
    private String description;
}
