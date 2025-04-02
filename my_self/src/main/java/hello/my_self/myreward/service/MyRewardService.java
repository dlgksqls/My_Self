package hello.my_self.myreward.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardUpdateDto;

import java.util.List;

public interface MyRewardService {
    MyReward create(MyRewardCreateDto createRewardDto);

    MyReward findById(Long id);

    MyReward findByName(String name);

    MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto);

    void delete(String name);
    List<MyReward> findByMember(Long memberId);
}
