package ui.Windows;

import exceptions.GibberishInput;
import model.Calender;
import model.FinishedList;
import model.TodoList;
import parser.WebReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MenuWindow extends UI {

    private String  viewTodosLable = " View Todo List";

    public MenuWindow(TodoList todolist, FinishedList finished, Calender calender) throws IOException {
        super(todolist, finished, calender);
        initializeTextArea();
        welcomeMessage();
        System.out.println();
        System.out.println("what can I do for you?");
    }

    @Override
    protected void initializeButton() {
        JButton viewTodos = createButton(" View Todo List",150,450);
        JButton viewByDate = createButton("View Tasks by Due Date",150,500);
        JButton viewFinished = createButton("View what you have done",150,550);
        JButton exit = createButton("Exit",150,600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(" View Todo List")){
            frame.dispose();
            try {
                new TodolistWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
        }
        else  if(e.getActionCommand().equals("View Tasks by Due Date")){
            try {
                frame.dispose();
                new CalenderWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
        }
        else  if(e.getActionCommand().equals("View what you have done")){
            try {
                new FinishedWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
            frame.dispose();
        }
        else  if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
    }

    private static void welcomeMessage() throws IOException {
        System.out.println("Welcome!");
        System.out.println();
        WebReader webReader = new WebReader();
        webReader.getWeather();
    }
}
