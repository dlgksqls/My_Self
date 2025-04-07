package hello.my_self.projectstack.repository;

import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.entity.ProjectStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectStackJpaRepository extends JpaRepository<ProjectStackEntity, Long> {

    @Query("select ps from ProjectStackEntity ps join fetch ps.myProjectEntity p where p.id = :projectId")
    List<ProjectStackEntity> findByProjectId(@Param("projectId") Long projectId);

    @Query("select ps " +
            "from ProjectStackEntity ps join fetch ps.myStackEntity s join fetch ps.myProjectEntity p " +
            "where p.id = :projectId and s.id = :stackId")
    ProjectStackEntity findByIdAndStackId(Long projectId, Long stackId);
}
