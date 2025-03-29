package hello.my_self.myreward.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.service.MemberService;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
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
    public MyReward create(MyRewardCreateDto createRewardDto) {
        Member member = memberService.findById(createRewardDto.getMemberId());
        MyReward myReward = new MyReward();
        myReward.create(createRewardDto, member);
        return myRewardRepository.save(myReward);
    }

    @Override
    public MyReward findById(Long id) {
        return myRewardRepository.findById(id);
    }

    @Override
    public MyReward findByName(String name) {
        return myRewardRepository.findByName(name);
    }

    @Override
    public MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto) {
        MyReward findReward = myRewardRepository.findByName(name);
        return myRewardRepository.update(findReward.getName(), myRewardUpdateDto);
    }

    @Override
    public void delete(String name) {
        MyReward findReward = myRewardRepository.findByName(name);
        myRewardRepository.delete(findReward.getName());
    }
}
