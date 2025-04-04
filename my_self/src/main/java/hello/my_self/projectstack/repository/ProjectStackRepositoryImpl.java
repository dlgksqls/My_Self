package hello.my_self.projectstack.repository;

import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.myproject.repository.MyProjectJpaRepository;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.mystack.entity.MyStackEntity;
import hello.my_self.mystack.repository.MyStackJpaRepository;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.projectstack.domain.ProjectStack;
import hello.my_self.projectstack.entity.ProjectStackEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class ProjectStackRepositoryImpl implements ProjectStackRepository {

    private final ProjectStackJpaRepository projectStackJpaRepository;
    private final MyProjectJpaRepository myProjectJpaRepository;
    private final MyStackJpaRepository myStackJpaRepository;
    @Override
    public ProjectStack save(ProjectStack projectStack) {
        MyProjectEntity myProjectEntity = myProjectJpaRepository.findById(projectStack.getProject().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 기술 프로젝트는 존재하지 않습니다"));

        MyStackEntity myStackEntity = myStackJpaRepository.findById(projectStack.getStack().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 기술 스택은 존재하지 않습니다"));

        return projectStackJpaRepository.save(
                ProjectStackEntity.toEntity(projectStack, myProjectEntity, myStackEntity)
        ).toDomain();
    }

    @Override
    public ProjectStack findByProjectStackId(Long projectStackId) {
        return null;
    }
    @Override
    public List<ProjectStack> findByProjectId(Long projectId) {
        return null;
    }

    @Override
    public ProjectStack findByProjectIdAndStackId(Long projectId, Long stackId) {
        return null;
    }

    @Override
    public void allDelete(Long projectStackId) {
    }

    public void deleteStackOnProjectIdAndStackId(Long projectId, Long stackId) {
    }

    public List<ProjectStack> findById(Long id) {
        return null;
    }

}
