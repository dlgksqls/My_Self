package hello.my_self.mystack.repository;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyStackRepositoryImpl implements MyStackRepository{
    @Override
    public MyStack save(MyStack myStack) {
        return null;
    }

    @Override
    public MyStack findById(Long id) {
        return null;
    }

    @Override
    public MyStack update(Long id, MyStackUpdateDto updateDto) {
        return null;
    }

    @Override
    public MyStack findByName(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
