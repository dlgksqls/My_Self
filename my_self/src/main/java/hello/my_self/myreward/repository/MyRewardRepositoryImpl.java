package hello.my_self.myreward.repository;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.entity.MyRewardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyRewardRepositoryImpl implements MyRewardRepository{

    private final MyRewardJpaRepository myRewardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MyReward save(MyReward myReward) {
        Optional<MemberEntity> getMember = memberJpaRepository.findById(myReward.getMember().getId());
        return myRewardJpaRepository.save(MyRewardEntity.toEntity(myReward, getMember)).toDomain();
    }

    @Override
    public MyReward findById(Long id) {
        return null;
    }

    @Override
    public MyReward findByName(String name) {
        return null;
    }

    @Override
    public MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto) {
        return null;
    }

    @Override
    public void delete(String name) {

    }
}
