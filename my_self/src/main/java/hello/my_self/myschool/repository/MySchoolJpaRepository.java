package hello.my_self.myschool.repository;

import hello.my_self.myschool.domain.MySchool;
import hello.my_self.myschool.entity.MySchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MySchoolJpaRepository extends JpaRepository<MySchoolEntity, Long> {
    @Query("select s from MySchoolEntity s join fetch s.memberEntity m where m.id =: memberId")
    List<MySchool> findByMemberId(@Param("memberId") Long memberId);
}
