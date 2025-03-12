package hello.my_self.member.service;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import lombok.RequiredArgsConstructor;

public interface MemberService {
    Member create(MemberCreateDto memberCreateDto);
}
