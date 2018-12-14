package ui.Windows;

import model.Calender;
import model.FinishedList;
import model.TodoList;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NotificationWindow implements ActionListener {
    protected JFrame frame;
    private int frameHeight = 200;
    private int frameWidth = 600;
    private int buttonHeight = 30;
    private int buttonWidth = 100;

    public NotificationWindow(String title, String notification) throws IOException {
        initializeFrame(title);
        playsound("windows_error.wav");
        JLabel label = createLabel(notification,100,0);
        JButton button = createButton("Sure!",250,50);
        button.setBackground(Color.CYAN);
        button.setOpaque(true);
    }

    protected void initializeFrame(String title){
        frame = new JFrame(title);
        frame.setSize(frameWidth,frameHeight);
        frame.setLocation(500,500);
        frame.getContentPane().setBackground(Color.WHITE );
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    protected JButton createButton(String buttonLable,int x,int y){
        JButton button = new JButton(buttonLable);
        button.setActionCommand(buttonLable);
        button.addActionListener(this);
        frame.add(button);
        button.setBounds(x+frame.getInsets().left,y+frame.getInsets().top,100,50);

        return button;
    }

    protected JLabel createLabel(String label, int x, int y){
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Serif",Font.PLAIN,24));
        frame.add(jLabel);
        jLabel.setBounds(x+frame.getInsets().left,y +frame.getInsets().top,400,50);

        return jLabel;
    }

    private void playsound(String file){
        File soundFile = new File(file);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (LineUnavailableException e) {
            }
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Sure!")){
            frame.dispose();
        }
    }
}
