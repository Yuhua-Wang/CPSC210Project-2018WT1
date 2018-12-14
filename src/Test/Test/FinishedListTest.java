package Test;

import model.Task;
import model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinishedListTest {
    TodoList finished = new TodoList();
    Task task = new Task();
    Task task2 = new Task();
    Task task3 = new Task();


    @BeforeEach
    void setUp() {
        finished = new TodoList();
        task.setTitle("test");
        task.setComment("it's a test");
    }

}
