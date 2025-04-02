package hello.my_self.mystack.repository;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackUpdateDto;

import java.util.List;

public interface MyStackRepository {
    MyStack save(MyStack myStack);

    MyStack findById(Long id);

    MyStack update(Long id, MyStackUpdateDto updateDto);

    MyStack findByName(String name);

    void delete(Long id);

    List<MyStack> findByMemberId(Long memberId);
}
