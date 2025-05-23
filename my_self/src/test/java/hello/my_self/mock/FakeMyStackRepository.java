package hello.my_self.mock;

import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.repository.MyStackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMyStackRepository implements MyStackRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<MyStack> data = new ArrayList<>();

    @Override
    public MyStack save(MyStack myStack) {
        MyStack newStack = MyStack.builder()
                .id(autoGeneratedId.incrementAndGet())
                .name(myStack.getName())
                .member(myStack.getMember())
                .build();

        data.add(newStack);
        return newStack;
    }

    @Override
    public MyStack findById(Long id) {
        return data.stream()
                .filter(stack -> stack.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 기술 스택은 없습니다."));
    }

    @Override
    public MyStack update(Long id, MyStackUpdateDto updateDto) {
        MyStack findStack = data.stream()
                .filter(stack -> stack.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 기술 스택은 없습니다."));

        findStack.update(updateDto);
        return findStack;
    }

    @Override
    public MyStack findByName(String name) {
        return data.stream()
                .filter(stack -> stack.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 기술 스택은 없습니다."));
    }

    @Override
    public void delete(Long id) {
        data.removeIf(stack -> stack.getId().equals(id));
    }

    @Override
    public List<MyStack> findByMemberId(Long memberId) {
        return data.stream()
                .filter(stack -> stack.getMember().getId().equals(memberId))
                .toList();
    }
}
