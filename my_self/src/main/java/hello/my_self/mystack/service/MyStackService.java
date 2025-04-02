package hello.my_self.mystack.service;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;

import java.util.List;

public interface MyStackService {
    MyStack create(MyStackCreateDto createDto);

    MyStack update(Long id, MyStackUpdateDto updateDto);

    MyStack findById(Long id);

    MyStack findByName(String name);

    void delete(Long id);
    List<MyStack> findByMember(Long memberId);
}
