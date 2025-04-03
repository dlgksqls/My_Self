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

    @Override
    public void allDelete(Long projectStackId) {
    }

    @Override
    public List<ProjectStack> findById(Long id) {
        return null;
    }
}
