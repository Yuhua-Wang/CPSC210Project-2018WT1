package ui.Windows;

import model.Calender;
import model.FinishedList;
import model.TodoList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class FinishedWindow extends UI {

    public FinishedWindow(TodoList todolist, FinishedList finished, Calender calender) throws IOException {
        super(todolist, finished, calender);
        initializeTextArea();
        finished.printItems();
    }

    @Override
    protected void initializeButton() {
        JButton back = createButton("Back the Main Menu",150,600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back the Main Menu")){
            super.frame.dispose();
            try {
                new MenuWindow(todolist,finished,calender);
            } catch (IOException e1) {
            }
        }
    }
}
