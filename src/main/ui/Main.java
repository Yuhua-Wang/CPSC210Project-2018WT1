package ui;

import model.Calender;
import model.FinishedList;
import model.TodoList;
import parser.WebReader;
import ui.Windows.MenuWindow;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static TodoList todolist;
    static FinishedList finished;
    static String option;
    static String todo_saving = "Todolist Saving";
    static String finished_saving = "Finished Saving";
    static Calender calender;


    public static void main(String[] args) throws IOException {
        start();
    }


    private static void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        todolist = new TodoList();
        finished = new FinishedList();
        calender = new Calender();
        todolist.initiate(todo_saving);
        finished.initiate(finished_saving);
        calender.initiate(todolist);

        option = "";

        new MenuWindow(todolist, finished, calender);

    }


    private static void welcomeMessage() throws IOException {
        System.out.println("Welcome!");
        WebReader webReader = new WebReader();
        webReader.getWeather();
    }

    private static void endMessage() {
        System.out.println("remember to do these:");
        todolist.printItems();
        System.out.println("Goodbye!");
    }
}
