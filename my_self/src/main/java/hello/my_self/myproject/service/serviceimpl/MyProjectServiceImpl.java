package hello.my_self.myproject.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.service.MemberService;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectCreateDto;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.myproject.service.MyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyProjectServiceImpl implements MyProjectService {

    private final MyProjectRepository myProjectRepository;
    private final MemberService memberService;

    @Override
    @Transactional
    public MyProject create(ProjectCreateDto createProjectDto) {
        MyProject newProject = new MyProject();
        Member getMember = memberService.findById(createProjectDto.getMemberId());
        newProject.create(createProjectDto, getMember);
        return myProjectRepository.save(newProject);
    }

    @Override
    public MyProject findByName(String name) {
        return myProjectRepository.findByName(name);
    }

    @Override
    public MyProject findById(Long id) {
        MyProject findProject = myProjectRepository.findById(id);
        return findProject;
    }

    @Override
    @Transactional
    public MyProject update(Long id, ProjectUpdateDto projectUpdateDto) {
        MyProject findProject = myProjectRepository.findById(id);
        return myProjectRepository.update(findProject.getId(), projectUpdateDto);
    }

    @Override
    @Transactional
    public void delete(String name) {
        myProjectRepository.delete(name);
    }

    @Override
    public List<MyProject> findByMember(Long memberId) {
        return myProjectRepository.findByMemberId(memberId);
    }
}
