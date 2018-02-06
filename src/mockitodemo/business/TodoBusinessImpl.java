package mockitodemo.business;

import java.util.ArrayList;
import java.util.List;

//TodoService is a dependency of TodoBusinessImpl
//TodoService can talk to a database or an external interface
import mockitodemo.data.TodoService;

public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    //This is the method we want to write tests for
    public List<String> retrieveTodosRelatedToSpring(String user) {

        //List is an interface used by ArrayList so can use all functions provided by List by declaring like this
        List<String> filteredTodos = new ArrayList<String>();

        //We don't really care about what or if there is an implementation for retrieveTodos because the things we
        // want to test is retrieveTodosRelatedToSpring and not retrieveTodos
        List<String> allTodos = todoService.retrieveTodos(user);

        //for each loop where the main list is allTodos
        for (String todo : allTodos) {
            if (todo.contains("Spring")) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
}