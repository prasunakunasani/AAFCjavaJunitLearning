package mockitodemo.data;
import java.util.List;

// External Service - Lets say this comes from WunderList
//For testing, need to do:
// Create TodoService Stub
// Test TodoBusinessImpl using TodoService Stub

public interface TodoService {
    List<String> retrieveTodos(String user);
}