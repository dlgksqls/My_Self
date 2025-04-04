package hello.my_self.projectstack.repository;

import hello.my_self.projectstack.entity.ProjectStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStackJpaRepository extends JpaRepository<ProjectStackEntity, Long> {
}
