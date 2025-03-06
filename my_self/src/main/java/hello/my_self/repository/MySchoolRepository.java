package hello.my_self.repository;

import hello.my_self.entity.MySchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySchoolRepository extends JpaRepository<MySchool, Long> {
}
