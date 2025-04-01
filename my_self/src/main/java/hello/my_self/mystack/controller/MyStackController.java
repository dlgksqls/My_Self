package hello.my_self.mystack.controller;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackCreateResponseDto;
import hello.my_self.mystack.dto.MyStackResponseDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.service.MyStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<MyStackResponseDto> update(Long id, MyStackUpdateDto updateDto) {
        MyStack myStack = myStackService.update(id, updateDto);
        return new ResponseEntity<>(MyStackResponseDto.response(myStack), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> delete(Long id) {
        myStackService.delete(id);
        return new ResponseEntity<>("삭제 완료", HttpStatus.ACCEPTED);
    }
}
