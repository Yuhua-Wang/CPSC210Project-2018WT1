package ui.Windows;

import model.Calender;
import model.FinishedList;
import model.Task;
import model.TodoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.deploy.uitoolkit.ToolkitStore.getWindowFactory;
import static javax.swing.BoxLayout.Y_AXIS;

public class TodolistWindow extends UI {

    private int buttonWindowWidth = 750;
    private int buttonWindowHeight = 400;
    private int buttonHeight = 80;
    private int buttonWidth = 700;

    private String addTaskLable = "Add a New Task";
    private String removeTaskLable = "Cross Off a Task";
    private String editTaskLable = "Edit a Task";
    private String backLable = "Back the Main Menu";

    public TodolistWindow(TodoList todolist, FinishedList finished, Calender calender) throws IOException {
        super(todolist, finished, calender);
    }

    @Override
    protected void initializeButton() {
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        for (Task t : todolist) {
            String buttonLabel = t.getTitle() + "     Due Date: " + t.getDueDate();
            JButton button = new JButton(buttonLabel);
            button.setMaximumSize(new Dimension(700, 80));
            button.setBackground(Color.CYAN);
            button.setOpaque(true);
            button.setActionCommand(t.toString());
            button.addActionListener(this);
            container.add(button);
        }

        JScrollPane scroll = new JScrollPane (container,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        scroll.setBounds(50+frame.getInsets().left,+frame.getInsets().top,700,550);

        JButton back = createButton("Back to Previous Menu", 170,650);
        JButton createTask = createButton("Create a New Task", 170, 600);
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
            for (Task t : todolist) {
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
