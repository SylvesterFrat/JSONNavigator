package com.omstead.JSONNavigator;

import java.util.ArrayList;
public class Students {
    private ArrayList<Student> students;

    //Getters and setters
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public void printStudents() {
        for (Student s : students) {
            System.out.println();
            s.printStudent();
        }
    }
    public void removeStudents(int i) {students.remove(i);}

    //Getting avg, min, max, range
    public double[] getAverage() {
        double[] avg = new double[10];
        double[] student;
        for (int i = 0; i < getStudents().size(); i++) {
            student = getStudents().get(i).getAverage(); //for efficiency so that it isn't referenced all the time
            for (int j = 0; j < 10; j++) {
                avg[j] += student[j];
            }
        }
        for (int i = 0; i < 10; i++) {
            avg[i] /= getStudents().size();
            avg[i] = (double) (Math.round(avg[i] * 100)) / 100; //rounding it
        }
        return avg;
    }
    public double getAverage(String category) {
        double avg = 0;
        for (int i = 0; i < students.size(); i++) {
            avg += students.get(i).getAverage(category);
        }
        avg /= students.size();
        avg = (double) (Math.round(avg * 100)) / 100;
        return avg;
    }
    public double[] getMaximum()    {
        double[] max = new double[10];
        for (int i = 0; i < 10; i++) {
            max[i] = Double.MIN_VALUE;
        }
        double[] student;
        for(int i = 0; i < getStudents().size(); i++) {
            student = getStudents().get(i).getMaximum();
            for (int j = 0; j < 10; j++) {
                if (max[j] < student[j]) {
                    max[j] = student[j];
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
        double student;
        for (int i = 0; i < students.size(); i++) {
            student = getStudents().get(i).getMaximum(category);
            for (int j = 0; j < getStudents().size(); j++) {
                if (max < student) {
                    max = student;
                }
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
        double[] student;
        for(int i = 0; i < getStudents().size(); i++) {
            student = getStudents().get(i).getMinimum();
            for (int j = 0; j < 10; j++) {
                if (min[j] > student[j]) {
                    min[j] = student[j];
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
        double student;

        for (int i = 0; i < students.size(); i++) {


            student = getStudents().get(i).getMinimum(category);
            for (int j = 0; j < getStudents().size(); j++) {
                if (min > student) {
                    min = student;
                }
            }
        }
        min = (double) (Math.round(min * 100)) / 100;
        return min;
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
        double range = 0;
        double min = getMinimum(category);
        double max = getMaximum(category);

        range = Math.abs(max-min);

        return range;
    }


    public void purge() {
        purgeStudents();
        purgeDays();
    }
    //Gets rid of all students where data is 0, as this means that the student did not fill anything in
    public void purgeStudents() {
        boolean isNull = true;
        for (int i = getSize()-1; i >= 0; i--) {
            double[] avg = getStudents().get(i).getAverage();
            for (Double num: avg) {
                if (num != 0) {
                    isNull = false;
                    break;
                }
            }
            if (isNull) {
                removeStudents(i);
            }
            isNull = true;
        }
    }
    //Gets rid of all days where data is 0, as this means that the student did not fill anything in that day
    public void purgeDays() {
        boolean isNull = true;
        for (int i = getSize()-1; i >= 0; i--) {
            Student days = getStudents().get(i);
            for (int j = days.getSize()-1; j >= 0; j--) {
                Day day = days.getDays().get(j);
                for (int k = 0; k < day.getAll().length; k++) {
                    if (!(day.getAll()[k]==0)) {
                        isNull = false;
                    }
                }
                if (isNull) {
                    days.removeDay(j);
                }
                isNull = true;


            }
        }
    }

    //returns size of the students array; useful to decrease verbosity
    public int getSize() {
        return students.size();
    }

    //returns array of standard deviations
    public double[] getDeviation() {
        double[] student;
        double[] avg = getAverage();
        double[] deviation = new double[avg.length];
        for (int i = 0; i < getSize(); i++) {
            student = getStudents().get(i).getAverage();
            for (int j = 0; j < student.length; j++) {
                deviation[j] += Math.pow((student[j] - avg[j]),2);
            }
        }
        for (int i = 0; i < deviation.length; i++) {
            deviation[i] = Math.sqrt(deviation[i] /= getSize());
            deviation[i] = (double) (Math.round(deviation[i] * 100)) / 100;
        }

        return deviation;

    }
    //returns array of variances
    public double[] getVariance() {
        double[] variance = getDeviation();
        for (int i = 0; i < variance.length; i++) {
            variance[i] = Math.pow(variance[i], 2);
        }
        for (int i = 0; i < variance.length; i++) {
            variance[i] = (double)(Math.round(variance[i] * 100)) / 100;
        }
        return variance;
    }



}

