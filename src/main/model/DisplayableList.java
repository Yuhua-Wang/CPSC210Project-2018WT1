package model;

import Interface.Loadable;
import Interface.Savable;
import exceptions.GibberishInput;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DisplayableList implements Savable, Loadable, Iterable<Task> {
    String savingSplit = "ʤɣɮ";
    String listTitle;
    String listComment;
    ArrayList<Task> displayable_list = new ArrayList<>();
    int number_of_tasks_allowed = 15;

// MODIFIES: this
// EFFECT: set up the list with proper title, comments and contents.
    abstract  void  initiate(String file_name) throws IOException;

// MODIFIES: nothing
// EFFECTS: print out all tasks in the todolist with their index number
    abstract void printItems();

// MODIFIES : this
// EFFECTS: add a task into the To_doList
    public void addItem(Task task){
        displayable_list.add(task);
    }

// MODIFIES: nothing
// EFFECTS: return the size of the todolist
    public int size(){
        return displayable_list.size();
    }

// MODIFIES: nothing
// EFFECTS: return the title of this todolist
    public String getTitle() {
        return listTitle;
    }

// MODIFIES: nothing
// EFFECTS: return the intro of this todolist
    public String getComment() {
        return this.listComment;
    }


// MODIFIES: this
// EFFECTS: load the todolist from the saving file
    @Override
    public void load(String saving_file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(saving_file));
        if (lines.equals("")) {
        }
        else {
            for (String line : lines) {
                Task task = new Task();
                String[] input = line.split(savingSplit);
                task.setTitle(input[0]);
                task.setComment(input[1]);
                try {
                task.getDueDate().setYear(input[2]);
                task.getDueDate().setMonth(input[3]);
                task.getDueDate().setDay(input[4]);
//                task.getDueDate().setHour(input[5]);
//                task.getDueDate().setMinute(input[6]);
                addItem(task);
                } catch (GibberishInput gibberishInput){
                    System.out.println("error in due date");
                }
            }
        }
    }

// MODIFIES: Todolist Saving.txt
// EFFECTS: save the todolist to the saving file
    @Override
    public void save(String saving_file) throws IOException {
        PrintWriter writer = new PrintWriter(saving_file,"UTF-8");
        ArrayList<Task> savelist = displayable_list;
        for (Task t : savelist){
            writer.println(t.getTitle()+ savingSplit +t.getComment()+ savingSplit
                    +t.getDueDate().save_Date(savingSplit));
        }
        writer.close();
    }

    @Override
    public Iterator<Task> iterator() {
        return displayable_list.iterator();
    }
}
