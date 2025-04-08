package hello.my_self.projectstack.service;

import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;

import java.util.List;

public interface ProjectStackService {
    ProjectStack create(ProjectStackCreateDto createDto);
    ProjectStack findById(Long id);
    List<ProjectStack> findByProjectId(Long projectId);
    void deleteByPsId(Long projectStackId);
    void deleteByProjectId(Long projectId);
    void deleteByProjectIdAndStackId(Long projectId, Long stackId);
}
