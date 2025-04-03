package hello.my_self.projectstack.repository;

import hello.my_self.projectstack.domain.ProjectStack;

import java.util.List;

public interface ProjectStackRepository {
    ProjectStack save(ProjectStack projectStack);

    ProjectStack findByProjectStackId(Long projectStackId);

    void allDelete(Long projectStackId);

    List<ProjectStack> findById(Long id);
}
