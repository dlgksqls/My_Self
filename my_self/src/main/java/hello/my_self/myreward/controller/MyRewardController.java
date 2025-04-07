package hello.my_self.myreward.controller;

import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardCreateResponseDto;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardResponse;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.service.MyRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myreward")
public class MyRewardController {

    private final MyRewardService myRewardService;

    @GetMapping("")
    public ResponseEntity<MyRewardResponse> getRewardById(String rewardName) {
        MyReward findReward = myRewardService.findByName(rewardName);
        return new ResponseEntity<>(MyRewardResponse.response(findReward), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MyRewardCreateResponseDto> create(MyRewardCreateDto createRewardDto) {
        MyReward newReward = myRewardService.create(createRewardDto);
        return new ResponseEntity<>(MyRewardCreateResponseDto.response(newReward), HttpStatus.CREATED);
    }

    @PatchMapping("{rewardName}")
    public ResponseEntity<MyRewardResponse> update(@PathVariable("rewardName") String rewardName, MyRewardUpdateDto myRewardUpdateDto) {
        MyReward updateReward = myRewardService.update(rewardName, myRewardUpdateDto);
        return new ResponseEntity<>(MyRewardResponse.response(updateReward), HttpStatus.OK);
    }
}
