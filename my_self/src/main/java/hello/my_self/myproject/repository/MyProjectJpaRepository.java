package hello.my_self.myproject.repository;

import hello.my_self.myproject.entity.MyProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyProjectJpaRepository extends JpaRepository<MyProjectEntity, Long> {
    MyProjectEntity findByName(String projectName);
}
