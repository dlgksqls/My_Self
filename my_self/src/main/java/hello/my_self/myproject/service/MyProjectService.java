package hello.my_self.myproject.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;

public interface MyProjectService {
    MyProject create(ProjectCreateDto createProjectDto);

    MyProject findByName(String name);

    MyProject update(String name, ProjectUpdateDto projectUpdateDto);

    void delete(String name);

    MyProject findById(Long id);
}
