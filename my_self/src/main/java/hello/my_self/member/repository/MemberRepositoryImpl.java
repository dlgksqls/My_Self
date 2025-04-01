package hello.my_self.member.repository;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final MemberJpaRepository memberJpaRepository;
    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.toEntity(member)).toDomain();
    }

    @Override
    public Member findById(Long id) {
        return memberJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 학생은 없습니다."))
                .toDomain();
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(MemberEntity.toEntity(member));
    }
}
