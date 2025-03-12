package hello.my_self.mystack.repository;

import hello.my_self.mystack.entity.MyStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStackJpaRepository extends JpaRepository<MyStackEntity, Long> {
}
