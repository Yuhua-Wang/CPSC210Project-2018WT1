package ui.Windows;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class DateTodoListWindow extends TodolistWindow {
    Date date;
    TodoList todolist;

    public DateTodoListWindow(TodoList todolist, FinishedList finished, Calender calender, Date date) throws IOException {
        super(date.getTodolist(), finished, calender);
        this.todolist = todolist;
        this.date = date;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back to Previous Menu")){
            super.frame.dispose();
            try {
                new MenuWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
        }

        else if(e.getActionCommand().equals("Create a New Task")){
            Task task = new Task();
            try {
                super.frame.dispose();
                new TaskWindow(todolist,finished,calender,task);
            } catch (IOException e1) { }
        }

        else {
            for (Task t : date.getTodolist()) {
                if (t.toString().equals(e.getActionCommand())){
                    try {
                        super.frame.dispose();
                        new TaskWindow(todolist,finished,calender,t);
                    } catch (IOException e1) { }
                }
            }
        }

    }
}
