package hello.my_self.myproject.repository;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.entity.MyProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyProjectJpaRepository extends JpaRepository<MyProjectEntity, Long> {
    @Query("select p from MyProjectEntity p where p.name = :projectName")
    MyProjectEntity findByName(@Param("projectName") String projectName);

    @Query("select p from MyProjectEntity p join fetch p.memberEntity m where m.id = :memberId")
    List<MyProjectEntity> findByMemberId(@Param("memberId") Long memberId);
}
