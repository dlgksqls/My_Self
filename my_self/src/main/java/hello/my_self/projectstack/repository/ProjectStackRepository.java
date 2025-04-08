package hello.my_self.projectstack.repository;

import hello.my_self.projectstack.domain.ProjectStack;

import java.util.List;

public interface ProjectStackRepository {
    ProjectStack save(ProjectStack projectStack);
    ProjectStack findById(Long id);
    List<ProjectStack> findByProjectId(Long projectId);
    ProjectStack findByProjectIdAndStackId(Long projectId, Long stackId);
    void delete(Long id);
    void allDelete(List<ProjectStack> projectStacks);
}
