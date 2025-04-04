package hello.my_self.mystack.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.mystack.dto.MyStackUpdateDto;
import hello.my_self.projectstack.entity.ProjectStackEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MyStackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "myStackEntity")
    private List<ProjectStackEntity> projectStackEntityList;

    public static MyStackEntity toEntity(MyStack myStack, MemberEntity member) {
        MyStackEntity myStackEntity = new MyStackEntity();
        myStackEntity.name = myStack.getName();
        myStackEntity.memberEntity = member;
        myStackEntity.projectStackEntityList = new ArrayList<>();

        return myStackEntity;
    }

    public MyStack toDomain(){
        return MyStack.builder()
                .id(id)
                .name(name)
                .member(memberEntity.toDomain())
                .build();
    }

    public void update(MyStackUpdateDto updateDto) {
        if (updateDto.getName() != null) this.name = updateDto.getName();
    }
}
