package model;

import SubjectAndObserver.Subject;
import exceptions.Cancel;

import java.util.Objects;
import java.util.Scanner;


public class Task extends Subject{
    String title="New Task";
    String comment=" ";
    Date dueDate = new Date();
    int repeat = 0;

// MODIFIES: this
// EFFECTS: set the title of the task to the given parameter
    public void setTitle (String title){
            this.title=title;
    }


// MODIFIES: nothing
// EFFECTS: return the title of this task
    public String getTitle(){
        return title;
    }



// MODIFIES: this
// EFFECTS: set the comment of the task to the given parameter
    public void setComment (String comment){
        this.comment=comment;
    }

// MODIFIES: nothing
// EFFECTS: return the comment of this task
    public String getComment(){
        return comment;
    }

// MODIFIES: this
// EFFECTS: set the due date of the task to the given parameter
  public void setDueDate (Date date){
        if (!dueDate.equals(date)){
            this.dueDate = date;
            dueDate.add(this);
        }
    }

// MODIFIES: nothing
// EFFECTS: return the due date of this task
    public Date getDueDate(){
        return dueDate;
    }


// MODIFIES: this
// EFFECTS: set the time to repeat to the given int
    public void setRepeat(int t){
        repeat = t;
    }

// MODIFIES: nothing
// EFFECTS: get the time to repeat
    public int getRepeat(){ return repeat; }

// MODIFIES: nothing
// EFFECTS :convert the task to a string that can be printed out
    @Override
    public String toString(){
        return title + "   Comments: "+ comment + "   Due date: " + dueDate;
    }

// MODIFIES: this
// EFFECTS: modify the given task according to users' input
    public void createTask() throws Cancel {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the title of this task ");
        String title = scanner.nextLine();
        setTitle(title);
        while (true){
            System.out.println(" ");
            System.out.println(toString());
            System.out.println(" ");
            System.out.println("To confirm, enter '1' ");
            System.out.println("To reset the title, enter '2' ");
            System.out.println("To set the comment, enter '3' ");
            System.out.println("To set the due date, enter '4' ");
            System.out.println("To cancel, enter 'cancel' ");
            String input = scanner.nextLine();
            if (input.equals("1")){
                toString();
                break;
            }
            else if (input.equals("2")){
                System.out.println("please enter the title of this task ");
                String newTitle = scanner.nextLine();
                setTitle(scanner.nextLine());
            }
            else if (input.equals("3")){
                System.out.println("please enter the any comments about this task  " +
                        "press 'Enter' if there's no comment ");
                setComment(scanner.nextLine());
            }
            else if (input.equals("4")){
                try {
                    dueDate.setDate(this);
                } catch (Cancel cancel){
                    dueDate = new Date();
                } catch (NumberFormatException e){
                    System.out.println("please enter a valid number");
                }
            }
            else if (input.equals("cancel")){
                throw new Cancel();
            }
            else {
                System.out.println("I'm sorry I don't understand");
            }
        }
    }

    public void changeTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("the task you chose is: ");
        while (true){
            System.out.println(" ");
            System.out.println(toString());
            System.out.println(" ");
            System.out.println("To confirm, enter '1' ");
            System.out.println("To reset the title, enter '2' ");
            System.out.println("To reset the comment, enter '3' ");
            System.out.println("To reset the due date, enter '4' ");
            System.out.println("To cancel, enter 'cancel' ");
            String input = scanner.nextLine();
            if (input.equals("1")){
                System.out.println("you just made a task:");
                System.out.println(toString());
                break;
            }
            else if (input.equals("2")){
                notifyBeforeChange(this);
                System.out.println("please enter the title of this task ");
                setTitle(scanner.nextLine());
                notifyAfterChange(this);
            }
            else if (input.equals("3")){
                notifyBeforeChange(this);
                System.out.println("please enter the any comments about this task  " +
                        "press 'Enter' if there's no comment ");
                setComment(scanner.nextLine());
                notifyAfterChange(this);
            }
            else if (input.equals("4")){
                notifyBeforeChange(this);
                try {
                    dueDate = new Date();
                    dueDate.setDate(this);
                } catch (Cancel cancel){
                    dueDate = new Date();
                } catch (NumberFormatException e){
                    System.out.println("please enter a valid number");
                }
                notifyAfterChange(this);
            }
            else {
                System.out.println("I'm sorry I don't understand");
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return repeat == task.repeat &&
                Objects.equals(title, task.title) &&
                Objects.equals(comment, task.comment) &&
                Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, comment, dueDate, repeat);
    }
}



