package hello.my_self.myschool.repository;

import hello.my_self.myschool.entity.MySchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySchoolJpaRepository extends JpaRepository<MySchoolEntity, Long> {
}
