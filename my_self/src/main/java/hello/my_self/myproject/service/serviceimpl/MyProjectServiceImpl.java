package hello.my_self.myproject.service.serviceimpl;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyProjectServiceImpl implements MyProjectService {

    private final MyProjectRepository myProjectRepository;

    @Override
    public MyProject create(ProjectCreateDto createProjectDto) {
        MyProject newProject = new MyProject();
        newProject.create(createProjectDto);
        return myProjectRepository.save(newProject);
    }

    @Override
    public MyProject findByName(String name) {
        return myProjectRepository.findByName(name);
    }

    @Override
    public MyProject update(String name, ProjectUpdateDto projectUpdateDto) {
        MyProject findProject = myProjectRepository.findByName(name);
        findProject.update(projectUpdateDto);
        return myProjectRepository.save(findProject);
    }

    @Override
    public void delete(String name) {
        myProjectRepository.delete(name);
    }
}
