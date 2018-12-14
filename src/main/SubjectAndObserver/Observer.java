package SubjectAndObserver;

import model.Task;

public interface Observer {

    void updateAfterChange(Task task);

    void updateBeforeChange(Task task);

}
