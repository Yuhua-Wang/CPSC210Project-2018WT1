package SubjectAndObserver;

import model.Task;

import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void notifyBeforeChange(Task task){
        for (Observer o: observers) {
            o.updateBeforeChange(task);
        }
    }

    public void  notifyAfterChange(Task task){
        for (Observer o: observers) {
            o.updateAfterChange(task);
        }
    }

    public void addObserver(Observer observer){
        if (! observers.contains(observer)){
        observers.add(observer);
        }
    }

    public  void  removeObserver (Observer observer){
        observers.remove(observer);
    }
}
