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
        return myRewardJpaRepository.save(MyRewardEntity.toEntity(myReward, getMember.get())).toDomain();
    }

    @Override
    public MyReward findById(Long id) {
        return myRewardJpaRepository.findById(id).get().toDomain();
    }

    @Override
    public MyReward findByName(String name) {
        return myRewardJpaRepository.findByName(name);
    }

    @Override
    public MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto) {
        MyReward myReward = myRewardJpaRepository.findByName(name);
        myReward.update(myRewardUpdateDto);
        return myReward;
    }

    @Override
    public void delete(String name) {
        MyReward myReward = myRewardJpaRepository.findByName(name);
        myRewardJpaRepository.delete(MyRewardEntity.toEntity(myReward, MemberEntity.toEntity(myReward.getMember())));
    }
}
