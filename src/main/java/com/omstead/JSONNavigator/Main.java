package com.omstead.JSONNavigator;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/*
Stuff used:
-classes
-object-oriented principles
-static methods
-try/catch error handling
 */

public class Main {
    private Scanner sc;
    private Students dataSet;
    private ArrayList<Student> allStudents;
    public Main() throws IOException {
        sc = new Scanner(System.in);
        FileReader input = new FileReader("data.json");
        Gson gson = new Gson();
        dataSet = gson.fromJson(input, Students.class);
        input.close();
        allStudents = dataSet.getStudents();

        dataSet.purge(); //removes null days and students
        printMenu();
        System.out.println("Press any key to begin the menu, then enter the option you would like to choose: ");
        int userInput = getNextInt();
        while (userInput != -1) {
            switch(userInput) {
                case 1:
                    checkHealthStatus();
                    break;
                case 2:
                    compareTwoStudents();
                    break;
                case 3:
                    printSubject();
                    break;
                case 4:
                    dataSet.printStudents();
                    break;
                case 5:
                    printMathStuff();
                    break;
                case 6:
                    printAnalysis();
                    break;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
            printMenu();
            userInput = sc.nextInt();
            sc.nextLine();

        }
        System.out.println("Thank you for viewing our data!");
        sc.close();
    }


    public void printMenu() {
        System.out.printf("%nPress: %n-1 to exit%n1 to check a student's health status%n2 to compare 2 students%n3 to display all data for 1 subject%n4 to display all data for all subjects%n5 to display average, maximum, minimum and range%n6 to display statistical analysis%n");
    }
    //switch case 1
    public void checkHealthStatus() {
        boolean runAgain = true;
        while (runAgain) {
            System.out.println("Checking health; please input id: ");
            int id = getNextInt()-1;

            if ((id < dataSet.getSize()) && (id > -1)) {
                allStudents.get(id).healthCheck();
                runAgain = false;
            } else {
                System.out.println("ID does not exist!");
            }
        }
    }
    //switch case 2
    public void compareTwoStudents() {
        boolean runAgain = true;
        int id1;
        int id2;
        while(runAgain) {
            System.out.println("Enter the ids of 2 students to compare them!");
            System.out.println("Student 1:");
            id1 = getNextInt()-1;
            System.out.println("Student 2:");
            id2 = getNextInt()-1;

            if (((id1 >= 0) && (id2 >= 0)) && ((id1 < dataSet.getSize()) && (id2 < dataSet.getSize()))) {
                if (id1 == id2) {
                    System.out.println("Ids are the same!");
                } else {
                    String[] comparison = Student.compareStudents(dataSet.getStudents().get(id1), dataSet.getStudents().get(id2));
                    for (String compare : comparison) {
                        System.out.println(compare);
                    }
                    runAgain = false;
                }
            } else {
                System.out.println("ID does not exist! Please try again");
            }
        }
    }
    //switch case 3
    public void printSubject() {
        boolean runAgain = true;
        while (runAgain) {
            System.out.println("Please enter the id of the student you would like to display");
            int id = getNextInt()-1;

            if ((id < dataSet.getSize()) && (id >= 0)) {
                allStudents.get(id).printStudent();
                runAgain = false;
            } else {
                System.out.println("ID does not exist!");
            }
        }
    }

    //switch case 4 + 5
    public void printMathStuff() {
        boolean runAgain = true;
        while (runAgain) {
            System.out.println("Would you like to enter a category? If so, type 'yes'. Otherwise, type 'no'");
            double[][] tendencies = new double[][]{dataSet.getAverage(), dataSet.getMaximum(), dataSet.getMinimum(), dataSet.getRange()};
            String userInput = sc.nextLine();
            if (userInput.equals("no")) {
                runAgain = false;
                System.out.printf("%12s", "");
                for (int i = 0; i < Day.getHeader().length; i++) {
                    System.out.printf("%12s", Day.getHeader()[i]);
                }
                System.out.println();
                for (int i = 0; i < Student.getTendencies().length; i++) {
                    System.out.printf("%12s", Student.getTendencies()[i]);
                    for (int j = 0; j < dataSet.getAverage().length; j++) {
                        System.out.printf("%12.2f", tendencies[i][j]);
                    }
                    System.out.println();

                }
            } else if (userInput.equals("yes")) {
                runAgain = false;
                printMathStuffWithCategory();
            } else {
                System.out.println("Please enter a valid choice!");
            }
        }
    }
    public void printMathStuffWithCategory() {
        boolean runAgain = true;
        while (runAgain) {
            System.out.println("Please print which category: ");
            String category = sc.nextLine();
            boolean isCategory = false;
            for (String cat: Day.getCategories()) {
                if (cat.equals(category)) {
                    isCategory = true;
                }
            }
            if (isCategory) {
                System.out.println("Average: " + dataSet.getAverage(category));
                System.out.println("Maximum: " + dataSet.getMaximum(category));
                System.out.println("Minimum: " + dataSet.getMinimum(category));
                System.out.println("Range: " + dataSet.getRange(category));
                runAgain = false;
            } else {
                System.out.println("Invalid category; please enter another! ");
            }
        }
    }
    //switch case 6
    public void printAnalysis() {
        System.out.println("Statistical analysis: ");
        double[] standardDeviations = dataSet.getDeviation();
        double[] variance = dataSet.getVariance();
        String[] categories = Day.getHeader();
        System.out.println("Standard deviation: ");
        for (int i = 0; i < standardDeviations.length; i++) {
            System.out.println(categories[i] + " " + standardDeviations[i]);
        }
        System.out.printf("%nVariance: %n");
        for (int i = 0; i < variance.length; i++) {
            System.out.printf("%s %.2f%n", categories[i], variance[i]);
        }
    }
    //gets the next integer, handles incorrect data types with a -1 which works for all of our purposes
    public int getNextInt() {
        try {
            int nextInt = Integer.parseInt(sc.nextLine());
            return nextInt;
        } catch (Exception NumberFormatException) {
            return -1;
        }
    }

    //main
    public static void main(String[] args) throws IOException {
        new Main();
    }
}
