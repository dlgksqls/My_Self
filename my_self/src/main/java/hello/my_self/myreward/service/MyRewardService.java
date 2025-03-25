package hello.my_self.myreward.service;

import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.RewardCreateDto;

public interface MyRewardService {
    MyReward create(RewardCreateDto createRewardDto);
}
