package model;

import SubjectAndObserver.Observer;
import exceptions.GibberishInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TodoList extends DisplayableList implements Observer, Iterable<Task>{
    String todo_saving = "Todolist Saving";

// MODIFIES: this
// EFFECT: set up the list with proper title, comments and contents.
    public void  initiate(String file_name) throws IOException {
        listTitle="Todo List";
        listComment = "";
        super.load(todo_saving);
    }

    @Override
    public void addItem(Task task){
        super.addItem(task);
        task.addObserver(this);
    }

// MODIFIES: nothing
// EFFECTS: print out all tasks in the todolist with their index number
    public void printItems(){
        int index = 1;
        for (Task t: displayable_list) {
            System.out.println( "["+Integer.toString(index)+"]"+ "   " + t);
            index = index+1;
        }
    }

// MODIFIES: nothing
// EFFECTS: return true if the TodoList contains the given Task
    public boolean contains(Task task){
        return displayable_list.contains(task);
    }

// MODIFIES: nothing
// EFFECTS: get the item in the list at the given index
    public Task getTask(int index){
        return displayable_list.get(index);
    }

// REQUIRE: task of the index number exist
// MODIFIES: this
// EFFECT: remove the task of the index number from the To_doList
    public void removeTaskByIndex (int index){
        displayable_list.remove(index);
    }

// MODIFIES: this
// EFFECT: remove the task  from the To_doList
    public void removeTaskByTask (Task task){
        displayable_list.remove(task);
    }

// MODIFIES: this
// EFFECTS: set the title of this todolist to the give parameter
    public void setTitle(String title) {
        this.listTitle = title;
    }

// MODIFIES: this
// EFFECTS: set the introduction of the todolist to the given parameter
    public void setComment(String comment) {
        this.listComment = comment;
    }

    public ArrayList<Task> getList(){
        return displayable_list;
    }

// MODIFIES: this, finished
// EFFECTS: delete a task from todolist and add it to finished
    public void crossOff(Calender calender, FinishedList finished) throws GibberishInput {
        Scanner scanner = new Scanner(System.in);
        int crossOff;
        printItems();
        System.out.println("which task do you want to cross off?");
        //String input = scanner.nextLine();
        crossOff = (Integer.parseInt(scanner.nextLine()) - 1);
        if ((crossOff < 0) | (crossOff+1 > size())){
            throw new GibberishInput();
        }
        Task crossOffTask = getTask(crossOff);
        finished.addItem(crossOffTask);
        calender.getDate(crossOffTask.getDueDate().toString()).remove(crossOffTask);
        removeTaskByIndex(crossOff);
        System.out.println("you've crossed off task  " + crossOff);
    }

    @Override
    public void updateBeforeChange(Task task) {
        removeTaskByTask(task);
    }

    @Override
    public void updateAfterChange(Task task) {
        addItem(task);
    }

    public void editTask() throws GibberishInput{
        Scanner scanner = new Scanner(System.in);
        int input;
        printItems();
        System.out.println("which task to you want to edit?");
        input = (Integer.parseInt(scanner.nextLine()) - 1);
        if ((input < 0) | (input+1 > size())){
            throw new GibberishInput();
        }
        Task taskChnaged = getTask(input);
        taskChnaged.changeTask();
    }

    @Override
    public Iterator<Task> iterator() {
        return displayable_list.iterator();
    }
}


