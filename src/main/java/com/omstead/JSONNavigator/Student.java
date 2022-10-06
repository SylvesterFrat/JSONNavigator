package com.omstead.JSONNavigator;

import java.util.ArrayList;
public class Student {

    private String id;
    private ArrayList<Day> days;

    public int getSize() {
        return days.size();
    }
    //Getters and Setters
    public ArrayList<Day> getDays() {
        return days;
    }
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
    public void removeDay(int dayId) {days.remove(dayId);}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    //compares two students; returns an array of the differences between them
    public static String[] compareStudents(Student s1, Student s2) {
        double[] comparison = new double[10]; //for either student, no error checking is needed because an empty double defaults to 0
        String[] returnComparison = new String[10];
        double[] s1avg = s1.getAverage();
        double[] s2avg = s2.getAverage();
        String[] measures = new String[] {"Sleep", "Sugar", "Phone", "Fruit", "Veggies", "Caffeine", "Strength", "Moderate", "High", "Steps"};
        //Divide everything by number of days in both; compare them
        for (int i = 0; i < 10; i++) {
            comparison[i] = s1avg[i] - s2avg[i];
            comparison[i] = (double) (Math.round(comparison[i] * 100)) / 100; //rounding it
        }
        for (int i = 0; i < 10; i++) {
            if (comparison[i] >= 0) {
                returnComparison[i] = "Student " + s1.getId() + " is " + comparison[i] + " higher in the category of " + measures[i];
            } else {
                returnComparison[i] = "Student " + s1.getId() + " is " + Math.abs(comparison[i]) + " lower in the category of " + measures[i];
            }
        }

        //Output
        return returnComparison;
    }
    //checks the health of a student
    public void healthCheck() {
        double[] healthyStandard = new double[] {420.0, 6.0, 120.0, 2.0, 2.0, 4.0, 10.0, 10.0, 5.0, 5000.0}; //sourced from Google; possibly inaccurate
        boolean[] aboveOrBelowGood = new boolean[] {true, false, false, true, true, false, true, true, true, true}; //more=true fruit and veggies, less=false sugar, less=false phone, etc. etc.
        boolean isHealthy = true;
        String[] measures = new String[] {"Sleep", "Sugar", "Phone", "Fruit", "Veggies", "Caffeine", "Strength", "Moderate", "High", "Steps"};
        double[] avg = getAverage(); //average over week for the student
        for (int i = 0; i < 10; i++) {
            //Checking if it should be above or below
            if (aboveOrBelowGood[i]) {
                if ((avg[i] < healthyStandard[i])) {
                    isHealthy = false;
                    System.out.println("You need more: " + measures[i]);
                }
            } else {
                if (avg[i] > healthyStandard[i]) {
                    isHealthy = false;
                    System.out.println("You need less: " + measures[i]);
                }
            }
        }
        if (isHealthy) {
            System.out.println("You are healthy! Congrats!");
        } else {
            System.out.println("Get healthy.");
        }


    }
    //Used to print a student's data + their averages, printStudents in Students calls this method for every individual student
    public void printStudent() {
        double[][] studentData = new double[days.size()][10];
        double[] studentAvgs = getAverage();
        String[] stats = Day.getHeader();
        for (int i = 0; i < getSize(); i++) {
            studentData[i] = getDays().get(i).getAll();
        }
        System.out.printf("%nStudent ID: %s%n", getId());
        System.out.printf("      ");
        for (String stat: stats) {
            System.out.printf("%-12s", stat);
        }
        for (int i = 0; i < studentData.length; i++) {
            System.out.println();
            System.out.printf("Day %d ", i+1);
            for (int j = 0; j < studentData[i].length; j++) {
                System.out.printf("%-7.2f     ", studentData[i][j]);
            }
        }

        System.out.printf("%nAvg   ");
        for (int k = 0; k < studentAvgs.length; k++) {
            System.out.printf("%-12.2f", studentAvgs[k]);
        }
    }
    //Returns the student's average, maximum, minimum, and range. Overloaded to also do categories.
    public double[] getAverage() {
        double[] avg = new double[10];
        double[] day;
        for (int i = 0; i < getSize(); i++) {
            day = getDays().get(i).getAll(); //for efficiency so that it isn't referenced all the time
            for (int j = 0; j < 10; j++) {
                avg[j] += day[j];
            }
        }
        for (int i = 0; i < 10; i++) {
            avg[i] /= getSize();
            avg[i] = (double) (Math.round(avg[i] * 100)) / 100; //rounding it
        }
        return avg;
    }
    public double getAverage(String category) {
        double avg = 0;
        int index = 0;
        String[] categories = Day.getCategories();
        for (int j = 0; j < categories.length; j++) {
            if (category.equals(categories[j])) {
                index = j;
            }
        }
        for (int i = 0; i < getSize(); i++) {
            avg += getDays().get(i).getAll()[index];
        }
        avg /= getSize();
        avg = (double) (Math.round(avg * 100)) / 100;
        return avg; //rounding it
    }
    public double[] getMaximum() {
        double[] max = new double[10];
        for (int i = 0; i < 10; i++) {
            max[i] = Double.MIN_VALUE;
        }
        double[] day;
        for(int i = 0; i < getSize(); i++) {
            day = getDays().get(i).getAll();
            for (int j = 0; j < 10; j++) {
                if (max[j] < day[j]) {
                    max[j] = day[j];
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            max[i] = (double) (Math.round(max[i] * 100)) / 100;
        }
        return max;
    }
    public double getMaximum(String category) {
        double max = Double.MIN_VALUE;
        int index = 0;
        double day;
        String[] categories = Day.getCategories();
        for (int j = 0; j < categories.length; j++) {
            if (category.equals(categories[j])) {
                index = j;
            }
        }
        for (int i = 0; i < getSize(); i++) {
            day = getDays().get(i).getAll()[index];
            if (day > max) {
                max = day;
            }
        }
        max = (double) (Math.round(max * 100)) / 100;
        return max;
    }
    public double[] getMinimum() {
        double[] min = new double[10];
        for (int i = 0; i < 10; i++) {
            min[i] = Double.MAX_VALUE;
        }
        double[] day;
        for(int i = 0; i < getSize(); i++) {
            day = getDays().get(i).getAll();
            for (int j = 0; j < 10; j++) {
                if (min[j] > day[j]) {
                    min[j] = day[j];
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            min[i] = (double) (Math.round(min[i] * 100)) / 100;
        }
        return min;
    }
    public double getMinimum(String category) {
        double min = Double.MAX_VALUE;
        int index = 0;
        double day;
        String[] categories = Day.getCategories();
        for (int j = 0; j < categories.length; j++) {
            if (category.equals(categories[j])) {
                index = j;
            }
        }
        for (int i = 0; i < getDays().size(); i++) {
            day = getDays().get(i).getAll()[index];
            if (min > day) {
                min = getDays().get(i).getAll()[index];
            }
        }
        min = (double) (Math.round(min * 100)) / 100;
        return min; //rounding it
    }
    public double[] getRange() {
        double[] range = new double[10];
        double[] min = getMinimum();
        double[] max = getMaximum();
        for (int i = 0; i < 10; i++) {
            range[i] = Math.abs((max[i]-min[i]));
        }
        return range;
    }
    public double getRange(String category) {
        return Math.abs(getMaximum(category)-getMinimum(category)); //just in case it gives -0.0 or something like that
    }
    //Similar purpose to getHeader in day
    public static String[] getTendencies() {
        return new String[] {"Average", "Maximum", "Minimum", "Range"};
    }
}

