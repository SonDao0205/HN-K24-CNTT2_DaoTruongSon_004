package rco.presentation;

import rco.business.CourseBusiness;
import rco.validator.InputValidate;

import java.util.Scanner;

public class CourseManagement {
    public static void displayMenu(Scanner sc, CourseBusiness courseBusiness) {
        int choice;
        do {
            String find;
            System.out.println("***************************QUẢN LÝ KHOÁ HỌC***************************");
            System.out.println("1. Hiển thị danh sách toàn bộ khoá học");
            System.out.println("2. Thêm mới khoá học");
            System.out.println("3. Cập nhật thông tin khoá học theo mã");
            System.out.println("4. Xoá khoá học theo mã");
            System.out.println("5. Tìm kiếm khoá học theo tên giảng viên");
            System.out.println("6. Lọc khoá học đang mở");
            System.out.println("7. Sắp xếp khoá học theo học phí giảm dần");
            System.out.println("8. Thoát");
            choice = InputValidate.getInt(sc, 0, 9,"Lựa chọn của bạn : ");
            switch (choice) {
                case 1:
                    courseBusiness.displayAll();
                    break;
                case 2:
                    while(true){
                        courseBusiness.addCourse(sc);
                        String ifContinue = InputValidate.getString(sc,"Bạn có muốn thêm tiếp không (Y/N) : ").toLowerCase();
                        if(!ifContinue.equals("y")){
                            break;
                        }
                    }
                    break;
                case 3:
                    find = InputValidate.getString(sc,"Nhập id khoá học bạn muốn cập nhật : ");
                    courseBusiness.updateCourse(sc, find);
                    break;
                case 4:
                    find = InputValidate.getString(sc,"Nhập id khoá học bạn muốn xoá : ");
                    courseBusiness.deleteCourse(find);
                    break;
                case 5:
                    find = InputValidate.getString(sc,"Nhập tên giảng viên muốn tìm : ");
                    courseBusiness.searchByInstructor(find);
                    break;
                case 6:
                    courseBusiness.filterActiveCourses();
                    break;
                case 7:
                    courseBusiness.sortByFeeDesc();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp!");
                    break;
            }
        } while (choice != 8);

    }
}
