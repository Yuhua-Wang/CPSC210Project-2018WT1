package Test;
import model.Task;
import model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListTest {
    TodoList todolist = new TodoList();
    Task task = new Task();
    Task task2 = new Task();
    Task task3 = new Task();


    @BeforeEach
     void setUp() {
        todolist = new TodoList();
        task.setTitle("test");
        task.setComment("it's a test");
    }



    @Test
     void addItemTest(){
        assertTrue(todolist.size() == 0);
        todolist.addItem(task);
        assertTrue(todolist.contains(task));
        assertTrue(todolist.size() == 1);
    }

    @Test
    void getTaskTest(){
        todolist.addItem(task);
        todolist.addItem(task2);
        Task test = todolist.getTask(0);
        assertTrue(test.getDueDate().equals("00.00"));
        assertTrue(test.getTitle().equals("test"));
        assertTrue(test.getComment().equals("it's a test"));
    }

    @Test
    void removeTaskByIndexTest(){
        todolist.addItem(task);
        todolist.addItem(task2);
        todolist.addItem(task3);
        todolist.removeTaskByIndex(1);
        assertFalse(todolist.contains(task2));
        assertTrue(todolist.size() == 2);
        todolist.removeTaskByIndex(0);
        assertFalse(todolist.contains(task2));
        assertFalse(todolist.contains(task));
        assertTrue(todolist.size() == 1);
    }

    @Test
    void sizeTest(){
        assertTrue(todolist.size() == 0);
        todolist.addItem(task);
        todolist.addItem(task2);
        todolist.addItem(task3);
        assertTrue(todolist.size() == 3);
    }

    @Test
    void containsTest(){
        assertFalse(todolist.contains(task));
        todolist.addItem(task);
        todolist.addItem(task2);
        assertTrue(todolist.contains(task));
        assertTrue(todolist.contains(task2));
        assertFalse(todolist.contains(task3));
    }

    @Test
    void loadTest() throws IOException {
        todolist.load("save test");
        assertTrue(todolist.size() == 0);
        todolist.load("load test");
        assertTrue(todolist.size() == 1);
        assertTrue(todolist.getTask(0).getTitle().equals( "test") );
        assertTrue(todolist.getTask(0).getComment().equals("it's a test" ));
        assertTrue(todolist.getTask(0).getDueDate().equals( "00.00"));
    }

    @Test
    void saveTest() throws IOException{
      todolist.addItem(task);
      todolist.save("save test");
      assertTrue(todolist.size() == 1);
      assertTrue(todolist.getTask(0).getTitle().equals( "test") );
      assertTrue(todolist.getTask(0).getComment().equals("it's a test" ));
      assertTrue(todolist.getTask(0).getDueDate().equals( "00.00"));
      todolist.removeTaskByIndex(0);
      todolist.save("save test");
    }
}
