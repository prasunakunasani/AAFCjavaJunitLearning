package mokitodemotest.business;

import mockitodemo.business.TodoBusinessImpl;
import mockitodemo.data.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

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
        assertEquals(2, filteredTodos.size());
    }

    @Test
    void retrieveTodosRelatedToSpring_usingBDD() {

        //Given
        //stubbing todoService mock
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring", "Learn Mockito", "Learn Junit", "Learn Spring MVC");

        //Instead of: when(todoServiceMock.retrieveTodos("DummyName")).thenReturn(todos); could do:
        given(todoServiceMock.retrieveTodos("DummyName")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        //invoking the specific method
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("DummyName");

        //Then
        //checking size
        //Insert of: assertEquals(2, filteredTodos.size()); could use:
        //Readable asserts - If the left value is the same as the right side value, test'll pass. Else it'll fail.
        assertThat(filteredTodos.size(), is(2));

    }

    @Test
    void deleteTodosNotRelatedToSpring_usingBDD() {

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring", "Learn Mockito", "Learn Junit", "Learn Spring MVC", "Learn Junit");
        given(todoServiceMock.retrieveTodos("DummyName")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("DummyName");

        //Then
        //want to check if deleteTodo was called with the parameter "Learn to Dance"
        verify(todoServiceMock).deleteTodo("Learn Mockito");
        //to check the second time this function is called by calling verify again
        //verify(todoServiceMock).deleteTodo("Learn Junit");

//verify that something is never called
        verify(todoServiceMock, never()).deleteTodo("Learn Spring");

        //verify that something was called specifically once (1) or twice(2)..etc
        verify(todoServiceMock, times(2)).deleteTodo("Learn Junit");

        //verify that a method was called at least once. It's okay if multiple times
        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn Junit");
        //verify that a mthod was called at least 5 times, etc
        verify(todoServiceMock, atLeast(5)).deleteTodo("Learn Mockito");
    }
}