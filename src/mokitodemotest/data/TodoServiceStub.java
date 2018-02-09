package mokitodemotest.data;

import mockitodemo.data.TodoService;

import java.util.Arrays;
import java.util.List;

//This was created just for test purposes since you're essentially hardcoding values
public class TodoServiceStub implements TodoService{

    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring", "Learn Mockito", "Learn Junit", "Learn Spring MVC");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
