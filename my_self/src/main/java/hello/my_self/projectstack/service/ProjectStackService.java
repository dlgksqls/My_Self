package hello.my_self.projectstack.service;

import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;

import java.util.List;

public interface ProjectStackService {
    ProjectStack create(ProjectStackCreateDto createDto);

    void allDelete(Long projectStackId);

<<<<<<< HEAD
    List<ProjectStack> findByProjectId(Long projectId);

    void deleteByProjectIdAndStackId(Long projectId, Long stackId);
=======
    List<ProjectStack> findById(Long id);
>>>>>>> origin/main
}
