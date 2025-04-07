package hello.my_self.myproject.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;

import java.util.List;

public interface MyProjectService {
    MyProject create(ProjectCreateDto createProjectDto);

    MyProject findByName(String name);

    MyProject update(Long id, ProjectUpdateDto projectUpdateDto);

    void delete(Long id);

    MyProject findById(Long id);

    List<MyProject> findByMember(Long memberId);
}
