package hello.my_self.member.dto;

import hello.my_self.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberCreateResponseDto {

    private String name;

    public static MemberCreateResponseDto response(Member member){
        return MemberCreateResponseDto.builder()
                .name(member.getName())
                .build();
    }
}
