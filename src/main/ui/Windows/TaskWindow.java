package ui.Windows;

import exceptions.GibberishInput;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class TaskWindow extends UI {
    private Task task;
    private JLabel titleLabel;
    private JLabel commentLabel;
    private JLabel dueLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;
    private JTextField titlefield;
    private JTextField commentfield;
    private JTextField yearfield;
    private JTextField monthfield;
    private JTextField dayfield;

    public TaskWindow(TodoList todolist, FinishedList finished, Calender calender, Task task) throws IOException {
        super(todolist, finished, calender);
        this.task = task;
        initializeLabelAndField(task);
    }

    protected  void initializeLabelAndField(Task t){
        titleLabel = createLabel("Title:", 100, 50);
        commentLabel = createLabel("Comment:",100,150);
        dueLabel = createLabel("Due Date:",100,250);
        monthLabel = createLabel("Month:",250,250);
        dayLabel = createLabel("Day:",250,300);
        yearLabel = createLabel("Year:",250,350);

        titlefield = createTextField(t.getTitle(),250,50,500);
        commentfield = createTextField(t.getComment(), 250, 150,500);
        monthfield = createTextField(t.getDueDate().getMonth().toString(),350,250,100);
        dayfield = createTextField(t.getDueDate().getDay().toString(),350,300,100);
        yearfield = createTextField(t.getDueDate().getYear().toString(),350,350,100);

    }
    @Override
    protected void initializeButton() {
        JButton back = createButton("Back to Previous Menu", 170,650);
        JButton remove = createButton("Remove This Task", 170,550);
        JButton Edit = createButton("Implement Changes", 170,450);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back to Previous Menu")){
            frame.dispose();
            try {
                new TodolistWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
        }

        else if (e.getActionCommand().equals("Remove This Task")){
            frame.dispose();
            finished.addItem(task);
            todolist.removeTaskByTask(task);
            saveList();
            try {
                new TodolistWindow(todolist,finished,calender);
                new NotificationWindow("Task Removed","You have successfully removed this task!");
            } catch (IOException e1) {
            }
        }


        else if (e.getActionCommand().equals("Implement Changes")){
            if (! userInputStyleCheck()){
                try {
                    frame.dispose();
                    new TaskWindow(todolist,finished,calender,task);
                    new NotificationWindow("Error","Please Enter Valid Title and/or Date");
                } catch (IOException e1) {
                }
            }

            else {
                Date newDate = new Date();
                try {
                    newDate.setMonth(monthfield.getText().trim());
                    newDate.setYear(yearfield.getText().trim());
                    newDate.setDay(dayfield.getText().trim());

                    task.notifyBeforeChange(task);
                    task.setTitle(titlefield.getText());
                    task.setComment(commentfield.getText());
                    task.setDueDate(newDate);
                    todolist.addItem(task);
                    calender.addDate(task);
                    try {
                        frame.dispose();
                        new TaskWindow(todolist,finished,calender,task);
                        new NotificationWindow("Success", "New Task has been Added");
                    } catch (IOException e1) { }

                } catch (GibberishInput gibberishInput) {
                    try {
                        frame.dispose();
                        new TaskWindow(todolist,finished,calender,task);
                        new NotificationWindow("Error","Please Enter Valid Date");
                    } catch (IOException e1) {
                    }
                } catch (NumberFormatException numE){
                    try {
                        frame.dispose();
                        new TaskWindow(todolist,finished,calender,task);
                        new NotificationWindow("Error","Please Enter a Valid Date");
                    } catch (IOException e1) {
                    }
                }
            }

            saveList();
        }
    }

    protected void saveList(){
        try {
            finished.save("Finished Saving");
            todolist.save("Todolist Saving");
        } catch (IOException e1) {
        }
    }

    protected boolean userInputStyleCheck(){
       if (titlefield.getText().trim().length() > 0){

           if ( (monthfield.getText().trim().length() == 0)|| (dayfield.getText().trim().length() > 0)
                   || (yearfield.getText().trim().length() > 0)){

               if ( (monthfield.getText().trim().length() > 0)&& (dayfield.getText().trim().length() > 0)
                       &&(yearfield.getText().trim().length() > 0)){
                   return  true;
               }
              else return false;
           }

           else return true;
       }
       else return false;
    }
}
