package hello.my_self.member.repository;

import hello.my_self.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Member findById(Long id);

    void delete(Member member);
}
