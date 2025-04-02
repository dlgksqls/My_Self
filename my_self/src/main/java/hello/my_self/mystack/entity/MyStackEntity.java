package hello.my_self.mystack.entity;

import hello.my_self.member.entity.MemberEntity;
import hello.my_self.myproject.entity.MyProjectEntity;
import hello.my_self.mystack.domain.MyStack;
import jakarta.persistence.*;

@Entity
public class MyStackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private MyProjectEntity myProjectEntity;

    public static MyStackEntity toEntity(MyStack myStack, MemberEntity member, MyProjectEntity project) {
        MyStackEntity myStackEntity = new MyStackEntity();
        myStackEntity.name = myStack.getName();
        myStackEntity.memberEntity = member;
        myStackEntity.myProjectEntity = project;
        project.getMyStackEntityList().add(myStackEntity);

        return myStackEntity;
    }

    public MyStack toDomain(){
        return MyStack.builder()
                .id(id)
                .name(name)
                .member(memberEntity.toDomain())
                .myProject(myProjectEntity.toDomain())
                .build();
    }
}
