package hello.my_self.member.controller;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberCreateResponseDto;
import hello.my_self.member.dto.MemberResponseDto;
import hello.my_self.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable long id){
        return ResponseEntity
                .ok()
                .body(MemberResponseDto.response(memberService.findById(id)));
    }

    @PostMapping("")
    public ResponseEntity<MemberCreateResponseDto> create(MemberCreateDto createDto) {
        Member newMember = memberService.create(createDto);
        return new ResponseEntity<>(MemberCreateResponseDto.response(newMember), HttpStatus.CREATED);
    }
}
