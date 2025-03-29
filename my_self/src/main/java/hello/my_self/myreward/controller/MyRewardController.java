package hello.my_self.myreward.controller;

import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateResponseDto;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardResponseDto;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.service.MyRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyRewardController {

    private final MyRewardService myRewardService;

    public ResponseEntity<MyRewardCreateResponseDto> create(MyRewardCreateDto createRewardDto) {
        MyReward newReward = myRewardService.create(createRewardDto);
        return new ResponseEntity<>(MyRewardCreateResponseDto.response(newReward), HttpStatus.CREATED);
    }

    public ResponseEntity<MyRewardResponseDto> update(String rewardName, MyRewardUpdateDto myRewardUpdateDto) {
        MyReward updateReward = myRewardService.update(rewardName, myRewardUpdateDto);
        return new ResponseEntity<>(MyRewardResponseDto.response(updateReward), HttpStatus.OK);
    }

    public ResponseEntity<MyRewardResponseDto> getRewardById(String rewardName) {
        MyReward findReward = myRewardService.findByName(rewardName);
        return new ResponseEntity<>(MyRewardResponseDto.response(findReward), HttpStatus.OK);
    }
}
