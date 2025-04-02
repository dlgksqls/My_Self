package hello.my_self.member.service;

import hello.my_self.member.dto.MemberRelatedDto;

public interface MemberRelatedService {
    MemberRelatedDto viewAllRelation(Long memberId);
}
