package hello.my_self.member.service;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;

public interface MemberService {
    Member create(MemberCreateDto memberCreateDto);

    Member findById(Long id);

    Member update(Long id, MemberUpdateDto memberUpdateDto);

    void delete(Member member);
}
