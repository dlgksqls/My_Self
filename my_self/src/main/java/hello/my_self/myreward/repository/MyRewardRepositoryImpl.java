package hello.my_self.myreward.repository;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.dto.MyRewardUpdateDto;
import hello.my_self.myreward.entity.MyRewardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyRewardRepositoryImpl implements MyRewardRepository{

    private final MyRewardJpaRepository myRewardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MyReward save(MyReward myReward) {
        MemberEntity getMember = memberJpaRepository.findById(myReward.getMember().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 멤버는 없습니다."));

        return myRewardJpaRepository.save(MyRewardEntity.toEntity(myReward, getMember)).toDomain();
    }

    @Override
    public MyReward findById(Long id) {
        return myRewardJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상은 없습니다"))
                .toDomain();
    }

    @Override
    public MyReward findByName(String name) {
        return myRewardJpaRepository.findByName(name).toDomain();
    }

    @Override
    public MyReward update(String name, MyRewardUpdateDto myRewardUpdateDto) {
        MyRewardEntity updateReward = myRewardJpaRepository.findByName(name);
        updateReward.update(myRewardUpdateDto);
        return updateReward.toDomain();
    }

    @Override
    public void delete(String name) {
        MyRewardEntity deleteReward = myRewardJpaRepository.findByName(name);
        myRewardJpaRepository.delete(deleteReward);
    }

    @Override
    public List<MyReward> findByMemberId(Long memberId) {
        List<MyRewardEntity> memberReward = myRewardJpaRepository.findByMemberId(memberId);
        List<MyReward> returnReward = new ArrayList<>();
        for (MyRewardEntity myRewardEntity : memberReward) {
            returnReward.add(myRewardEntity.toDomain());
        }

        return returnReward;
    }
}
