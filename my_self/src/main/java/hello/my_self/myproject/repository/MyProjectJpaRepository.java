package hello.my_self.myproject.repository;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.entity.MyProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyProjectJpaRepository extends JpaRepository<MyProjectEntity, Long> {
    MyProjectEntity findByName(String projectName);

    void deleteByName(String name);

    @Query("select p from MyProjectEntity p join fetch p.memberEntity m where m.id =: memberId")
    List<MyProject> findByMemberId(@Param("memberId") Long memberId);
}
