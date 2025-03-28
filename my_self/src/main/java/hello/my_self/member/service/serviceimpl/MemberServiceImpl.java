package hello.my_self.member.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.dto.MemberUpdateDto;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Member create(MemberCreateDto createDto) {
        Member newMember = new Member();
        newMember.create(createDto);
        return memberRepository.save(newMember);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    @Transactional
    public Member update(Long id, MemberUpdateDto memberUpdateDto) {
        Member updateMember = memberRepository.findById(id);
        updateMember.update(memberUpdateDto);
        return memberRepository.save(updateMember);
    }

    @Override
    @Transactional
    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
