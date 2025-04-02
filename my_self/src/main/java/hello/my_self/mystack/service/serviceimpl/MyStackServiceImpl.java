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

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyStackServiceImpl implements MyStackService {

    private final MyStackRepository myStackRepository;
    private final MemberRepository memberRepository;
    private final MyProjectRepository myProjectRepository;
    @Override
    public MyStack create(MyStackCreateDto createDto) {
        Member member = memberRepository.findById(createDto.getMemberId());
        MyProject project = myProjectRepository.findById(createDto.getProjectId());

        MyStack myStack = new MyStack();
        myStack.create(createDto, member, project);
        return myStackRepository.save(myStack);
    }

    @Override
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
    public void delete(Long id) {
        myStackRepository.delete(id);
    }

    @Override
    public List<MyStack> findByMember(Long memberId) {
        return myStackRepository.findByMemberId(memberId);
    }
}
