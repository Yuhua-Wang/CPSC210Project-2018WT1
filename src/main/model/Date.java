package model;

import SubjectAndObserver.Observer;
import SubjectAndObserver.Subject;
import exceptions.Cancel;
import exceptions.GibberishInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Date{
    String year;
    String month;
    String day;
    TodoList todolist;
    String initial = " ";

    public Date(){
        year = initial;
        month = initial;
        day = initial;
        todolist = new TodoList();
    }

    public void setYear(String year) throws GibberishInput {
        timeStyleCheck(year,1000,9999);
        this.year = year;
    }

    public void setMonth(String month) throws GibberishInput {
        timeStyleCheck(month,1,12);
        this.month = month;
    }


    public void setDay(String day) throws GibberishInput {
        if (day.equals(initial)){ this.day = day;}
        else {
            int month_num = Integer.parseInt(month);
            if (month_num == 2) {
                if (Integer.parseInt(year) % 4 == 0){
                    timeStyleCheck(day,1,29);
                    this.day = day;
                }
                else {
                    timeStyleCheck(day,1,28);
                    this.day = day;}
            }

            else if ((month_num == 1 || month_num == 3 || month_num == 5 || month_num == 7 || month_num == 8
                    || month_num == 10 || month_num == 12)) {
                timeStyleCheck(day,1,31);
                this.day = day;
            }

            else timeStyleCheck(day,1,30);
            this.day = day;
        }
    }

//    public void setHour(String hour) throws GibberishInput {
//        int hour_num = Integer.parseInt(hour);
//        if (hour_num < 1 || hour_num > 24){
//            throw new GibberishInput();
//        }
//        this.hour = hour;
//    }
//
//    public void setMinute(String minute) throws GibberishInput {
//        int minute_num = Integer.parseInt(minute);
//        if (minute_num < 1 || minute_num > 59){
//            throw new GibberishInput();
//        }
//        this.minute = minute;
//    }

    private void timeStyleCheck(String input,int min, int max) throws GibberishInput {
        if (input.equals(initial)){ }
        else {
            int num = Integer.parseInt(input);
            if (Integer.parseInt(input) < min || Integer.parseInt(input) > max){
                throw new GibberishInput();
            }
        }
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

//    public String getHour() {
//        return hour;
//    }

    public void add(Task task){
        if (! todolist.contains(task)){
            this.todolist.addItem(task);
            task.setDueDate(this);
        }
    }

    public void remove(Task task){
        todolist.removeTaskByTask(task);
    }

    public String toString(){
        return year+"."+month+"."+day;
    }

    public TodoList getTodolist() {
        return todolist;
    }

    public void merge(Date date){
        for (Task task : date.getTodolist()) {
            add(task);
        }
    }

    public void removeTask(Task task){
        todolist.removeTaskByTask(task);
    }

    public void setDate(Task task) throws Cancel {
        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            System.out.println("");
            System.out.println("please enter the year");
            input = scanner.nextLine();
            Integer.parseInt(input);
            setYear(input);
            System.out.println("");
            System.out.println("please enter the month");
            input = scanner.nextLine();
            Integer.parseInt(input);
            setMonth(input);
            System.out.println("");
            System.out.println("please enter the day");
            input = scanner.nextLine();
            Integer.parseInt(input);
            setDay(input);
            System.out.println("");
//            System.out.println("please enter the hour");
//            setHour(scanner.nextLine());
//            System.out.println("");
//            System.out.println("please enter the minute");
//            setMinute(scanner.nextLine());
            System.out.println("");
            System.out.println("the due date is:  " + toString());
        } catch (GibberishInput gibberishInput){
            System.out.println("please enter a valid date");
            setDate(task);
        } catch (NumberFormatException e){
            System.out.println("please enter a valid date");
            setDate(task);
        }

        while (true){
            System.out.println(" ");
            System.out.println("To confirm, enter '1' ");
            System.out.println("To reset enter '2' ");
            System.out.println("To cancel, enter '3' ");
            input = scanner.nextLine();
            if (input.equals("1")) {
                add(task);
            break;
            }
            else if (input.equals("2")){
                setDate(task);
                break;
            }
            else if (input.equals("3")){
                throw new Cancel();
            }
            else {
                System.out.println("I'm sorry I don't understand");
            }
        }
    }

    public String save_Date(String split){
        return year+split+month+split+day;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(year, date.year) &&
                Objects.equals(month, date.month) &&
                Objects.equals(day, date.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    public void printTasks() {
        for (Task t: todolist) {
            System.out.println(t.toString());
        }
    }
}
