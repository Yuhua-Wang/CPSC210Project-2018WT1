package ui.Windows;

import model.Calender;
import model.FinishedList;
import model.TodoList;
import parser.WebReader;
import ui.SoutRedirect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public abstract class UI implements ActionListener {
    static TodoList todolist;
    static FinishedList finished;
    static Calender calender;
    protected JFrame frame;
    private  JTextArea textArea;
    private int frameHeight = 800;
    private int frameWidth = 800;
    private int buttonHeight = 30;
    private int buttonWidth = 400;

    public UI(TodoList todolist, FinishedList finished, Calender calender) throws IOException {
        this.todolist = todolist;
        this.finished = finished;
        this.calender = calender;
        initialize();
    }

    private  void  initialize(){
        initializeFrame();
        initializeButton();
    }

    protected  void initializeFrame() {
        frame = new JFrame("Simple Todo List");
        frame.setSize(frameWidth,frameHeight);
        frame.setLocation(500,500);
        frame.getContentPane().setBackground(Color.WHITE );
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    protected void initializeTextArea(){
        textArea = new JTextArea(10, 40);
        textArea.setFont(new Font("Serif",Font.PLAIN,24));
        frame.add(textArea);
        //textArea.setBounds(frame.getInsets().left,frame.getInsets().top,800,400);

        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        scroll.setBounds(frame.getInsets().left,frame.getInsets().top,750,400);

        PrintStream printStream = new PrintStream(new SoutRedirect(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    protected JButton createButton(String buttonLable,int x,int y){
        JButton button = new JButton(buttonLable);
        button.setActionCommand(buttonLable);
        button.addActionListener(this);
        button.setBackground(Color.CYAN);
        button.setOpaque(true);
        frame.add(button);
        button.setBounds(x+frame.getInsets().left,y+frame.getInsets().top,buttonWidth,buttonHeight);

        return button;
    }

    protected JLabel createLabel(String label, int x, int y){
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Serif",Font.PLAIN,24));
        frame.add(jLabel);
        jLabel.setBounds(x+frame.getInsets().left,y +frame.getInsets().top,100,50);

        return jLabel;
    }

    protected JTextField createTextField(String text, int x, int y,int width){
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Serif",Font.PLAIN,24));
        frame.add(textField);
        textField.setBounds(x+frame.getInsets().left,y +frame.getInsets().top,width,50);

        return textField;
    }

    protected abstract void initializeButton();
}