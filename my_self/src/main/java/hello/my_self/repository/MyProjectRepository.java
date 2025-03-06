package hello.my_self.repository;

import hello.my_self.entity.MyProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyProjectRepository extends JpaRepository<MyProject, Long> {
}
