package hello.my_self.myreward.domain;

import hello.my_self.member.domain.Member;
import hello.my_self.member.dto.MemberCreateDto;
import hello.my_self.member.entity.Sex;
import hello.my_self.myreward.dto.MyRewardCreateDto;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MyRewardTest {

    @Test
    public void Reward_도메인은_RewardCreateDto로_새로운_객체를_생성할_수_있다(){
        // given
        MemberCreateDto member = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member newMember = new Member();
        newMember.create(member);

        MyRewardCreateDto createDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .build();
        // when
        MyReward reward = new MyReward();
        reward.create(createDto, newMember);

        // then
        assertThat(reward.getName()).isEqualTo("장려상");
        assertThat(reward.getHost()).isEqualTo("계명대학교 산학인재원");
        assertThat(reward.getNumber()).isEqualTo("K-38");
        assertThat(reward.getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(reward.getMember()).isEqualTo(newMember);
    }

    @Test
    public void MyReward_도메인은_RewardUpdateDto_로_새로운_객체를_수정할_수_있다(){
        // given
        MemberCreateDto member = MemberCreateDto.builder()
                .name("이한빈")
                .birth(LocalDate.of(2000, 10, 6))
                .age(26)
                .sex(Sex.male)
                .description("안녕하세요 개발자입니다.")
                .build();

        Member newMember = new Member();
        newMember.create(member);

        MyRewardCreateDto createDto = MyRewardCreateDto.builder()
                .name("장려상")
                .host("계명대학교 산학인재원")
                .number("K-38")
                .description("캡스톤 디자인 장려상입니다.")
                .build();

        MyReward reward = new MyReward();
        reward.create(createDto, newMember);

        // when
        MyRewardUpdateDto updateDto = MyRewardUpdateDto.builder()
                .name("최우수상")
                .host("계명대학교")
                .number("K-111")
                .build();

        reward.update(updateDto);

        // then
        assertThat(reward.getName()).isEqualTo("최우수상");
        assertThat(reward.getHost()).isEqualTo("계명대학교");
        assertThat(reward.getNumber()).isEqualTo("K-111");
        assertThat(reward.getDescription()).isEqualTo("캡스톤 디자인 장려상입니다.");
        assertThat(reward.getMember()).isEqualTo(newMember);
    }
}
