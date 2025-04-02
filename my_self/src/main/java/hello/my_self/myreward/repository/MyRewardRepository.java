package hello.my_self.myreward.repository;

import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardUpdateDto;

import java.util.List;

public interface MyRewardRepository {
    MyReward save(MyReward myReward);

    MyReward findById(Long id);

    MyReward findByName(String name);

    MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto);

    void delete(String name);

    List<MyReward> findByMemberId(Long memberId);
}
