package hello.my_self.myreward.repository;

import hello.my_self.myreward.domain.MyReward;
import hello.my_self.myreward.entity.MyRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyRewardJpaRepository extends JpaRepository<MyRewardEntity, Long> {
    MyReward findByName(String name);

    @Query("select r from MyRewardEntity r join fetch r.memberEntity m where m.id =: memberId")
    List<MyReward> findByMemberId(@Param("memberId") Long memberId);
}
