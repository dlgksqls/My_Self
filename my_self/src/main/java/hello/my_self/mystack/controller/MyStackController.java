package hello.my_self.mystack.controller;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackCreateResponseDto;
import hello.my_self.mystack.dto.MyStackResponse;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.service.MyStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mystack")
public class MyStackController {

    private final MyStackService myStackService;

    @PostMapping("")
    public ResponseEntity<MyStackCreateResponseDto> create(MyStackCreateDto createDto){
        MyStack myStack = myStackService.create(createDto);
        return new ResponseEntity<>(MyStackCreateResponseDto.response(myStack), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MyStackResponse> update(@PathVariable("id") Long id, MyStackUpdateDto updateDto) {
        MyStack myStack = myStackService.update(id, updateDto);
        return new ResponseEntity<>(MyStackResponse.response(myStack), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        myStackService.delete(id);
        return new ResponseEntity<>("삭제 완료", HttpStatus.ACCEPTED);
    }
}
