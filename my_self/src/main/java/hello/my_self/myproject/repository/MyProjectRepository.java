package hello.my_self.myproject.repository;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectUpdateDto;

import java.util.List;

public interface MyProjectRepository {
    MyProject save(MyProject newProject);

    MyProject findByName(String projectName);

    void delete(String name);

    MyProject findById(Long id);
    MyProject update(Long id, ProjectUpdateDto updateDto);

    List<MyProject> findByMemberId(Long memberId);
}