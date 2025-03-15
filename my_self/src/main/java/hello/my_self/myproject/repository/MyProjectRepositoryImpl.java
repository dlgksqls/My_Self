package hello.my_self.myproject.repository;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.entity.MyProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyProjectRepositoryImpl implements MyProjectRepository{

    private final MyProjectJpaRepository myProjectJpaRepository;

    @Override
    public MyProject save(MyProject newProject) {
        return myProjectJpaRepository.save(MyProjectEntity.toEntity(newProject)).toDomain();
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
    public void delete(String name) {
        myProjectJpaRepository.deleteByName(name);
    }
}
