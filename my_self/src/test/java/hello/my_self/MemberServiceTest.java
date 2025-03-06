package hello.my_self;

import hello.my_self.entity.Member;
import hello.my_self.repository.MemberRepository;
import hello.my_self.service.interfaces.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    @Test
    public void memberJoinTest() throws Exception{
        // given
        Member member = new Member();
        MemberJoinDto dto =
        member.createMember()

        // when

        // then
    }
}
