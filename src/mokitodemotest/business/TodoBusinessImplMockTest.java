package mokitodemotest.business;

import mockitodemo.business.TodoBusinessImpl;
import mockitodemo.data.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoBusinessImplMockTest {

    @Test
    void retrieveTodosRelatedToSpring_usingAMock() {

        //Originally:  TodoService todoServiceStub = new TodoServiceStub();
        //When mockTodoService .retrieveTodos("Parameter") is called, mock makes it return a value just like stub did (dynamically creating the stub)
                //Like: stub(mockTodoService.retrieveTodos("Parameter")).return("value");
        //mock is a method in the mockito class - can mock a class or an interface
        TodoService todoServiceMock = mock(TodoService.class);

        //before, you stubbed the output of retrieveTodos so instead now:
            //when the retrievetodos is called, then return the todos list
            //also, you can just remove this and change assertEquals to 0 since mockito sends a default value
        List<String> todos = Arrays.asList("Learn Spring", "Learn Mockito", "Learn Junit", "Learn Spring MVC");
        when(todoServiceMock.retrieveTodos("DummyName")).thenReturn(todos);

        //you're sending the stub to initiate the todoService in TodoBusinessImpl. So, it uses the sub function
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("DummyName");

        //this assert will pass since in retrieveTodos in the stub, two of the todos have spring in it
        assertEquals(2,filteredTodos.size());
    }
}