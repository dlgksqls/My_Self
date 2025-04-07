package hello.my_self.projectstack.domain;

import hello.my_self.myproject.domain.MyProject;
import hello.my_self.mystack.domain.MyStack;
import hello.my_self.projectstack.dto.ProjectStackCreateDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStack {

    private Long id;
    private MyProject project;
    private MyStack stack;

    public ProjectStack() {
    }

    public ProjectStack(Long id, MyProject project, MyStack stack) {
        this.id = id;
        this.project = project;
        this.stack = stack;
    }

    public void create(MyProject project, MyStack stack) {
        this.project = project;
        this.stack = stack;
    }

    public void createFirstProjectStack(MyProject myProject, MyStack myStack) {
        this.project = myProject;
        this.stack = myStack;
    }
}
