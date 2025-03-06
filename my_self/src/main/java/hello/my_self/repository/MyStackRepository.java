package hello.my_self.repository;

import hello.my_self.entity.MyStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStackRepository extends JpaRepository<MyStack, Long> {
}
