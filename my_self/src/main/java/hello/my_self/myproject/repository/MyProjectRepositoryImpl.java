package hello.my_self.myproject.repository;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.dto.ProjectUpdateDto;
import hello.my_self.myproject.entity.MyProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyProjectRepositoryImpl implements MyProjectRepository{

    private final MyProjectJpaRepository myProjectJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MyProject save(MyProject newProject) {
        MemberEntity getMember = memberJpaRepository.findById(newProject.getMember().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 멤버는 없습니다."));
        return myProjectJpaRepository.save(MyProjectEntity.toEntity(newProject, getMember)).toDomain();
        // Member 를 영속성객체에 저장한 후 Project 를 저장해야하는데,, domain 으로는 해결하기 어려워서 그냥 entity 를 불러옴,,,
    }

    @Override
    public MyProject findById(Long id) {
        return myProjectJpaRepository.findById(id).get().toDomain();
    }

    @Override
    public MyProject findByName(String projectName) {
        return myProjectJpaRepository.findByName(projectName).toDomain();
    }

    @Override
    public MyProject update(Long id, ProjectUpdateDto updateDto) {
        Optional<MyProjectEntity> findProject = myProjectJpaRepository.findById(id);
        findProject.get().update(updateDto);
        return findProject.get().toDomain();
    }

    @Override
    public List<MyProject> findByMemberId(Long memberId) {
        List<MyProjectEntity> memberProject = myProjectJpaRepository.findByMemberId(memberId);
        List<MyProject> returnProject = new ArrayList<>();

        for (MyProjectEntity myProjectEntity : memberProject) {
            returnProject.add(myProjectEntity.toDomain());
        }

        return returnProject;
    }

    @Override
    public void delete(Long id) {
        MyProjectEntity deleteProject = myProjectJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트는 없습니다."));
        myProjectJpaRepository.delete(deleteProject);
    }
}
