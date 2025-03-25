package hello.my_self.myreward.domain;


import hello.my_self.member.domain.Member;
import hello.my_self.myreward.dto.RewardCreateDto;
import hello.my_self.myreward.dto.RewardUpdateDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyReward {

    private Long id;
    private String name;
    private String host;
    private String number;
    private String description;
    private Member member;

    public MyReward(Long id, String name, String host, String number, String description, Member member) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.number = number;
        this.description = description;
        this.member = member;
    }

    public MyReward() {
    }

    public void create(RewardCreateDto reward, Member member){
        this.name = reward.getName();
        this.host = reward.getHost();
        this.number = reward.getNumber();
        this.description = reward.getDescription();
        this.member = member;
    }

    public void update(RewardUpdateDto reward){
        if (reward.getName() != null) this.name = reward.getName();
        if (reward.getHost() != null) this.host = reward.getHost();
        if (reward.getNumber() != null) this.number = reward.getNumber();
        if (reward.getDescription() != null) this.description = reward.getDescription();
    }
}
