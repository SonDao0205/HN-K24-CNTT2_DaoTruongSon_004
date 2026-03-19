package rco;

import rco.business.CourseBusiness;
import rco.presentation.CourseManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseBusiness courseBusiness = CourseBusiness.getInstance();
        CourseManagement.displayMenu(sc, courseBusiness);
        sc.close();
    }
}
