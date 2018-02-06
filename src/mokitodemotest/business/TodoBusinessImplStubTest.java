package mokitodemotest.business;

import mockitodemo.business.TodoBusinessImpl;
import mockitodemo.data.TodoService;
import mokitodemotest.data.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoBusinessImplStubTest {

    @Test
    void retrieveTodosRelatedToSpring_usingAstub() {

        TodoService todoServiceStub = new TodoServiceStub();

        //you're sending the stub to initiate the todoService in TodoBusinessImpl. So, it uses the sub function
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("DummyName");

        //this assert will pass since in retrieveTodos in the stub, two of the todos have spring in it
        assertEquals(2,filteredTodos.size());
    }
}