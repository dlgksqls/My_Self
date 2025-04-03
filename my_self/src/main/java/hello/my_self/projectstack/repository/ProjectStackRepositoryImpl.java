package hello.my_self.projectstack.repository;

import hello.my_self.projectstack.domain.ProjectStack;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectStackRepositoryImpl implements ProjectStackRepository {
    @Override
    public ProjectStack save(ProjectStack projectStack) {
        return null;
    }

    @Override
    public ProjectStack findByProjectStackId(Long projectStackId) {
        return null;
    }
<<<<<<< HEAD
    @Override
    public List<ProjectStack> findByProjectId(Long projectId) {
        return null;
    }

    @Override
    public ProjectStack findByProjectIdAndStackId(Long projectId, Long stackId) {
        return null;
    }
=======
>>>>>>> origin/main

    @Override
    public void allDelete(Long projectStackId) {
    }

    @Override
<<<<<<< HEAD
    public void deleteStackOnProject(Long projectId, Long stackId) {

=======
    public List<ProjectStack> findById(Long id) {
        return null;
>>>>>>> origin/main
    }
}
