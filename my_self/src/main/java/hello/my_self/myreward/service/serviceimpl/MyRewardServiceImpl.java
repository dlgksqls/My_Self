package hello.my_self.myreward.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.service.MemberService;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.RewardCreateDto;
import hello.my_self.myreward.repository.MyRewardRepository;
import hello.my_self.myreward.service.MyRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyRewardServiceImpl implements MyRewardService {

    private final MyRewardRepository myRewardRepository;
    private final MemberService memberService;

    @Override
    public MyReward create(RewardCreateDto createRewardDto) {
        Member member = memberService.findById(createRewardDto.getMemberId());
        MyReward myReward = new MyReward();
        myReward.create(createRewardDto, member);
        return myRewardRepository.save(myReward);
    }
}
