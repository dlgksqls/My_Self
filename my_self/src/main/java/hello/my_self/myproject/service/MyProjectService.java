package hello.my_self.myproject.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;

public interface MyProjectService {
    MyProject create(ProjectCreateDto createProjectDto);

    MyProject findByName(String name);
}
