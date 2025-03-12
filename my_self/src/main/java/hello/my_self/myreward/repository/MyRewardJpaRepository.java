package hello.my_self.myreward.repository;

import hello.my_self.myreward.entity.MyRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRewardJpaRepository extends JpaRepository<MyRewardEntity, Long> {
}
