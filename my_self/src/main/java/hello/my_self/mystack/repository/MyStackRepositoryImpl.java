package hello.my_self.mystack.repository;

import hello.my_self.member.domain.Member;
import hello.my_self.member.entity.MemberEntity;
import hello.my_self.member.repository.MemberJpaRepository;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.myproject.repository.MyProjectJpaRepository;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.entity.MyStackEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class MyStackRepositoryImpl implements MyStackRepository{

    private final MyStackJpaRepository myStackJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final MyProjectJpaRepository myProjectJpaRepository;
    @Override
    public MyStack save(MyStack myStack) {
        MemberEntity member = memberJpaRepository.findById(myStack.getMember().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 멤버는 없습니다."));

        MyProjectEntity project = myProjectJpaRepository.findById(myStack.getMyProject().getId())
                .orElseThrow(() -> new NoSuchElementException("해당 프로젝트는 없습니다."));

        return myStackJpaRepository.save(MyStackEntity.toEntity(myStack, member, project)).toDomain();
    }

    @Override
    public MyStack findById(Long id) {
        return myStackJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 스택은 없습니다."))
                .toDomain();
    }

    @Override
    public MyStack update(Long id, MyStackUpdateDto updateDto) {
        MyStackEntity findStack = myStackJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 스택은 없습니다."));

        findStack.update(updateDto);
        return findStack.toDomain();
    }

    @Override
    public MyStack findByName(String name) {
        MyStackEntity findStack = myStackJpaRepository.findByName(name);
        return findStack.toDomain();
    }

    @Override
    public void delete(Long id) {
        MyStackEntity deleteEntity = myStackJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 스택은 없습니다."));
        myStackJpaRepository.delete(deleteEntity);
    }

    @Override
    public List<MyStack> findByMemberId(Long memberId) {
        List<MyStackEntity> memberStack = myStackJpaRepository.findByMemberId(memberId);
        List<MyStack> returnStack = new ArrayList<>();
        for (MyStackEntity myStackEntity : memberStack) {
            returnStack.add(myStackEntity.toDomain());
        }

        return returnStack;
    }
}
