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

import java.util.ArrayList;
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
    public ProjectStack findById(Long id) {
        return projectStackJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 연관관계는 없습니다.")).toDomain();
    }
    @Override
    public List<ProjectStack> findByProjectId(Long projectId) {
        MyProjectEntity myProjectEntity = myProjectJpaRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트는 존재하지 않습니다"));

        List<ProjectStackEntity> entityList = projectStackJpaRepository.findByProjectId(myProjectEntity.getId());

        List<ProjectStack> returnList = new ArrayList<>();
        for (ProjectStackEntity projectStackEntity : entityList) {
            returnList.add(projectStackEntity.toDomain());
        }

        return returnList;
    }

    @Override
    public ProjectStack findByProjectIdAndStackId(Long projectId, Long stackId) {
        MyProjectEntity myProjectEntity = myProjectJpaRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트는 존재하지 않습니다"));

        MyStackEntity myStackEntity = myStackJpaRepository.findById(stackId)
                .orElseThrow(() -> new NoSuchElementException("해당 스택은 존재하지 않습니다"));

        ProjectStackEntity projectStackEntity = projectStackJpaRepository.findByIdAndStackId(myProjectEntity.getId(), myStackEntity.getId());
        return projectStackEntity.toDomain();
    }

    @Override
    public void delete(Long id) {
        ProjectStackEntity projectStackEntity = projectStackJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 관계는 존재하지 않습니다"));

        projectStackJpaRepository.delete(projectStackEntity);
    }

    @Override
    public void allDelete(List<ProjectStack> projectStacks) {
        for (ProjectStack projectStack : projectStacks) {
            ProjectStackEntity projectStackEntity = projectStackJpaRepository.findById(projectStack.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 관계는 존재하지 않습니다"));

            projectStackJpaRepository.delete(projectStackEntity);
        }
    }
}
