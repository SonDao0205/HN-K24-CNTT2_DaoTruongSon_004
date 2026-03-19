package rco.entity;

import rco.validator.InputValidate;

import java.util.Comparator;
import java.util.Scanner;

public class Course{
    private String courseId;
    private String courseName;
    private int credit;
    private double tuitionFee;
    private String instructor;
    private boolean status;
    public static int courseCount = 1;

    public Course(String courseName, int credit, double tuitionFee, String instructor) {
        this.courseId = "CO" + (courseCount < 10 ? "00" + courseCount : courseCount > 100 ? courseCount : "0" + courseCount);
        this.status = true;
        this.courseName = courseName;
        this.credit = credit;
        this.tuitionFee = tuitionFee;
        this.instructor = instructor;
    }

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner){
        this.courseId = "CO" + (courseCount < 10 ? "00" + courseCount : courseCount > 100 ? courseCount : "0" + courseCount);
        this.status = true;
        String inputCourseName = InputValidate.getString(scanner,"Nhập tên khoá học : ");
        setCourseName(inputCourseName);
        int inputCourseCredit = InputValidate.getInt(scanner,0,10,"Nhập số tín chỉ : ");
        setCredit(inputCourseCredit);
        double inputTuitionFee = InputValidate.getDouble(scanner,0,9999999,"Nhập học phí : ");
        setTuitionFee(inputTuitionFee);
        String inputInstructor = InputValidate.getString(scanner,"Nhập tên giảng viên phụ trách : ");
        setInstructor(inputInstructor);
    }

    public void displayData(){
        System.out.printf("|%-10s|%-15s|%-8d|%-15.2f|%-15s|%-10s|\n",this.courseId,this.courseName,this.credit,this.tuitionFee,this.instructor,this.status);
    }
}
