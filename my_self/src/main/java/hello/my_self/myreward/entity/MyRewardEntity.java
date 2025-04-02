package hello.my_self.myreward.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class MyRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String host;
    private String number; // 등록 번호
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static MyRewardEntity toEntity(MyReward myReward, MemberEntity getMember) {
        MyRewardEntity myRewardEntity = new MyRewardEntity();
        myRewardEntity.id = myReward.getId();
        myRewardEntity.name = myReward.getName();
        myRewardEntity.description = myReward.getDescription();
        myRewardEntity.host = myReward.getHost();
        myRewardEntity.number = myReward.getNumber();
        myRewardEntity.memberEntity = getMember;
        getMember.getMyRewardEntityList().add(myRewardEntity);

        return myRewardEntity;
    }

    public MyReward toDomain() {
        return MyReward.builder()
                .id(id)
                .name(name)
                .host(host)
                .description(description)
                .number(number)
                .member(memberEntity.toDomain())
                .build();
    }

    public void update(MyRewardUpdateDto reward){
        if (reward.getName() != null) this.name = reward.getName();
        if (reward.getHost() != null) this.host = reward.getHost();
        if (reward.getNumber() != null) this.number = reward.getNumber();
        if (reward.getDescription() != null) this.description = reward.getDescription();
    }
}
