package hello.my_self.member.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public Member create(MemberCreateDto createDto) {
        Member newMember = new Member();
        newMember.createMember(createDto);
        return memberRepository.save(newMember);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member update(Long id, MemberUpdateDto memberUpdateDto) {
        Member updateMember = memberRepository.findById(id);
        updateMember.update(memberUpdateDto);
        return memberRepository.save(updateMember);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
