package model;

import SubjectAndObserver.Observer;
import exceptions.GibberishInput;

import java.util.*;

public class Calender implements Observer, Iterable<Date> {
    Map<String,Date> calender;

    public Calender(){
        calender = new HashMap<>();
    }

    public void initiate (TodoList todolist){
        for (Task task : todolist.getList()) {
            Date date = task.getDueDate();
            date.add(task);
            addDate(task);
        }
    }

    public void addDate(Task task){
        Date date = task.getDueDate();
        String key = date.toString();
        if (calender.containsKey(key)){
            calender.get(key).merge(date);
        } else {
            calender.put(key,date);
        }
        task.addObserver(this);
    }

    public Date getDate(String date){
        return calender.get(date);
    }

// MODIFIES: nothing
// EFFECTS: print out all tasks in the todolist with their index number
    public void printItems(){
        System.out.println(calender.keySet());
    }

    public void display() throws GibberishInput {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calender.keySet());
        System.out.println("please enter the date you want to check");
        String input = scanner.nextLine();
        if (! calender.containsKey(input)){
            throw new GibberishInput();
        }
        calender.get(input).printTasks();
    }

    @Override
    public void updateBeforeChange(Task task) {
        Date date = task.getDueDate();
        String key = date.toString();
        calender.get(key).removeTask(task);
    }

    @Override
    public void updateAfterChange(Task task) {
        addDate(task);
    }

    @Override
    public Iterator<Date> iterator() {
        ArrayList<Date> dates = new ArrayList<>();
        for(Date date : calender.values()){
            dates.add(date);
        }
        return dates.iterator();
    }
}
