package hello.my_self.myproject.repository;

import hello.my_self.myproject.domain.MyProject;

public interface MyProjectRepository {
    MyProject save(MyProject newProject);

    MyProject findByName(String projectName);

    void delete(String name);

    MyProject findById(Long id);
}
