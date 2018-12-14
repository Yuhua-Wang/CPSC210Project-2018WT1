package Test;

import exceptions.Cancel;
import exceptions.GibberishInput;
import model.Calender;
import model.FinishedList;
import model.Task;
import model.TodoList;
import java.io.IOException;
import java.util.Scanner;
import parser.*;

public class OldMain {
    static TodoList todolist;
    static FinishedList finished;
    static String option;
    static String todo_saving = "Todolist Saving";
    static String finished_saving = "Finished Saving";
    static Calender calender;


    public static void main(String[] args) throws IOException {
        start();
    }


    private static void start() throws IOException{
        Scanner scanner = new Scanner(System.in);
        todolist = new TodoList();
        finished = new FinishedList();
        calender = new Calender();
        option = "";

        welcomeMessage();

        todolist.initiate(todo_saving);
        finished.initiate(finished_saving);
        calender.initiate(todolist);

        while (true) {
            System.out.println("     ");
            System.out.println("What can I do for you?");
            System.out.println("Enter '1': add a new task.");
            System.out.println( "Enter '2':view the todo list.  ");
            System.out.println("Enter '3': view task by due date  ");
            System.out.println("Enter '4': view what you have done.  ");
            System.out.println("Enter 'quit' to quit.");
            option = scanner.nextLine();
            System.out.println("you selected: " + option);

            if (option.equals("1")) {
                try {
                    Task task = new Task();
                    task.createTask();
                    todolist.addItem(task);
                    calender.addDate(task);
                } catch (Cancel cancel) {
                }
                todolist.save(todo_saving);
            }

            else if (option.equals("2")) {
                while (true){
                    todolist.printItems();
                    System.out.println(" ");
                    System.out.println("Enter '1': back to the main menu");
                    System.out.println("Enter '2': cross off a task.  ");
                    System.out.println("Enter '3': edit a task");
                    option = scanner.nextLine();
                    if (option.equals("1")){
                        break;
                    }
                    else if (option.equals("2")){
                        try {
                            todolist.crossOff(calender,finished);
                        } catch (NumberFormatException n) {
                            System.out.println("Please enter a valid number!");
                        }catch (GibberishInput gibberishInput) {
                            System.out.println("the task you chose doesn't exist");
                        } finally {
                            todolist.save(todo_saving);
                            finished.save(finished_saving);
                        }
                    }
                    else if (option.equals("3")){
                        try {
                            todolist.editTask();
                        } catch (NumberFormatException n) {
                            System.out.println("Please enter a valid number!");
                        }catch (GibberishInput gibberishInput) {
                            System.out.println("the task you chose doesn't exist");
                        } finally {
                            todolist.save(todo_saving);
                            finished.save(finished_saving);
                        }
                    }
                }
            }

            else if (option.equals("3")){
                try {
                    calender.display();
                } catch (GibberishInput gibberishInput) {
                    System.out.println("no tasks due on that date");
                }
            }

            else if (option.equals("4")) {
                finished.printItems();
            }

            else if (option.equals("quit")) {
                todolist.save(todo_saving);
                finished.save(finished_saving);
                break;
            }

            else {
                System.out.println("I'm sorry I don't understand");
            }
        }
        endMessage();
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
