package hello.my_self;

import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MySelfApplication {

    private final MemberRepository memberRepository;
    public static void main(String[] args) {
        SpringApplication.run(MySelfApplication.class, args);
    }

    @PostConstruct
    void init(){
        Member member = new Member();
        member.createFistMember();
        memberRepository.save(member);
    }
}
