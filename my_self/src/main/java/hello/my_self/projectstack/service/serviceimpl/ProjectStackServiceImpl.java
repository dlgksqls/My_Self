package hello.my_self.projectstack.service.serviceimpl;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import hello.my_self.projectstack.repository.ProjectStackRepository;
import hello.my_self.projectstack.service.ProjectStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectStackServiceImpl implements ProjectStackService {

    private final MyProjectRepository myProjectRepository;
    private final MyStackRepository myStackRepository;
    private final ProjectStackRepository projectStackRepository;


    @Override
    public ProjectStack create(ProjectStackCreateDto createDto) {
        MyProject project = myProjectRepository.findById(createDto.getProjectId());
        MyStack stack = myStackRepository.findById(createDto.getStackId());
        ProjectStack projectStack = new ProjectStack();
        projectStack.create(project, stack);

        return projectStackRepository.save(projectStack);
    }

    @Override
    public void allDelete(Long projectStackId) {
        ProjectStack projectStack = projectStackRepository.findByProjectStackId(projectStackId);
        projectStackRepository.allDelete(projectStack.getId());
    }

    @Override
    public List<ProjectStack> findById(Long id) {
        return projectStackRepository.findById(id);
    }
}
