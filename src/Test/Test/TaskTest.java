package Test;

import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
     void getters_addersTest(){
        Task task = new Task();
        task.setTitle("test");
        task.setComment("it's a test");
        assertTrue(task.getTitle() == "test");
        assertTrue(task.getComment() == "it's a test");
    }
}
