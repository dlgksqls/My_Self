package hello.my_self.mystack.repository;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.entity.MyStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyStackJpaRepository extends JpaRepository<MyStackEntity, Long> {
    @Query("select t from MyStackEntity t join fetch t.memberEntity m where m.id =: memberId")
    List<MyStack> findByMemberId(@Param("memberId") Long memberId);
}
