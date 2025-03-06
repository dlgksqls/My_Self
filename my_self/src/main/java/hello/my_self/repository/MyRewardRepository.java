package hello.my_self.repository;

import hello.my_self.entity.MyReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRewardRepository extends JpaRepository<MyReward, Long> {
}
