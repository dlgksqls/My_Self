package hello.my_self.member.repository;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final MemberJpaRepository memberJpaRepository;
    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.be(member)).toDomain();
    }
}
