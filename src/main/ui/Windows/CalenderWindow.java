package ui.Windows;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CalenderWindow extends UI {
    protected Date date;

    public CalenderWindow(TodoList todolist, FinishedList finished, Calender calender) throws IOException {
        super(todolist, finished, calender);
    }

    @Override
    protected void initializeButton() {
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        for (Date date : calender) {
            String buttonLabel = date.toString();
            JButton button = new JButton(buttonLabel);
            button.setMaximumSize(new Dimension(700, 80));
            button.setBackground(Color.CYAN);
            button.setOpaque(true);
            button.setActionCommand(date.toString());
            button.addActionListener(this);
            container.add(button);
        }

        JScrollPane scroll = new JScrollPane (container,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        scroll.setBounds(50+frame.getInsets().left,+frame.getInsets().top,700,550);

        JButton back = createButton("Back to Previous Menu", 170,650);
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

        else {
            for (Date d : calender) {
                if (d.toString().equals(e.getActionCommand())){
                    try {
                        super.frame.dispose();
                        new DateTodoListWindow(todolist,finished,calender, d);
                    } catch (IOException e1) { }
                }
            }
        }
    }
}
