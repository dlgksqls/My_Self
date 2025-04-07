package hello.my_self.mystack.service.serviceimpl;

import hello.my_self.member.domain.Member;
import hello.my_self.member.repository.MemberRepository;
import hello.my_self.myproject.domain.MyProject;
import hello.my_self.myproject.repository.MyProjectRepository;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackCreateDto;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.mystack.repository.MyStackRepository;
import hello.my_self.mystack.service.MyStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MyStackServiceImpl implements MyStackService {

    private final MyStackRepository myStackRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public MyStack create(MyStackCreateDto createDto) {
        Member member = memberRepository.findById(createDto.getMemberId());
        MyStack myStack = new MyStack();
        myStack.create(createDto, member);
        return myStackRepository.save(myStack);
    }

    @Override
    @Transactional
    public MyStack update(Long id, MyStackUpdateDto updateDto) {
        MyStack myStack = myStackRepository.findById(id);
        return myStackRepository.update(myStack.getId(), updateDto);
    }

    @Override
    public MyStack findById(Long id) {
        return myStackRepository.findById(id);
    }

    @Override
    public MyStack findByName(String name) {
        return myStackRepository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        myStackRepository.delete(id);
    }

    @Override
    public List<MyStack> findByMember(Long memberId) {
        return myStackRepository.findByMemberId(memberId);
    }
}
