package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FinishedList extends DisplayableList {
    String finished_saving = "Finished Saving";


// MODIFIES: this
// EFFECT: set up the list with proper title, comments and contents.
    public void  initiate(String file_name) throws IOException {
        listTitle="Finished List";
        listComment = "Tasks that you have done";
        super.load(finished_saving);
    }

// MODIFIES: nothing
// EFFECTS: print out all tasks in the list, the one added the last should be printed out first
    public void printItems() {
        ArrayList<Task> print = new ArrayList<>();
        for (Task t: displayable_list) {
            print.add(t);
        }
        Collections.reverse(print);
        for (Task t: print) {
            System.out.println("   " + t);
        }
    }

// MODIFIES: Finished Saving.txt
// EFFECTS: save the 15 most recent done tasks
    @Override
    public void save(String saving_file) throws IOException {
        if (displayable_list.size() > number_of_tasks_allowed){
            for (int i = (displayable_list.size()-number_of_tasks_allowed);i>0;i=i-1){
                displayable_list.remove(0);
            }
        }
        super.save(saving_file);
    }
}



