package rco.business;

import rco.entity.Course;
import rco.validator.InputValidate;

import java.util.*;

public class CourseBusiness {
    private static CourseBusiness instance;
    private static final List<Course> courseList = new ArrayList<>();

    static{
        courseList.add(new Course("Java",3,300000,"Sơn"));
        Course.courseCount++;
        courseList.add(new Course("Python",4,400000,"Hưng"));
        Course.courseCount++;
        courseList.add(new Course("NodeJs",5,500000,"Hoàn"));
        Course.courseCount++;
        courseList.add(new Course("C++",6,600000,"Sơn"));
        Course.courseCount++;
        courseList.add(new Course("ok",3,300000,"Sơn"));
        Course.courseCount++;
    }

    private CourseBusiness() {
    }

    public static CourseBusiness getInstance() {
        if (instance == null) {
            instance = new CourseBusiness();
        }
        return instance;
    }

    public void displayAll(){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        printTableHeader();
        for(Course course : courseList){
            course.displayData();
            System.out.println("+------------------------------------------------------------------------------+");
        }
    }

    private Course findById(String id){
        return courseList.stream().filter(v -> v.getCourseId().equals(id)).findFirst().orElse(null);
    }

    public void addCourse(Scanner sc){
        Course newCourse = new Course();
        while(true){
            newCourse.inputData(sc);
            if(findById(newCourse.getCourseId()) != null){
                System.out.println("Mã khoá học đã tồn tại! Nhập lại!");
            }else{
                courseList.add(newCourse);
                System.out.println("Thêm khoá học thành công!");
                Course.courseCount++;
                return;
            }
        }
    }

    public void updateCourse(Scanner sc,String id){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        Optional<Course> findCourse = Optional.ofNullable(findById(id));
        if(findCourse.isEmpty()){
            System.out.println("Không tìm thấy khoá học phù hợp!");
            return;
        }
        while(true){
            System.out.println("Chọn trường thông tin cập nhật : ");
            System.out.println("1. Tên");
            System.out.println("2. Tín chỉ");
            System.out.println("3. Học phí");
            System.out.println("4. Giảng viên phụ trách");
            System.out.println("5. Trạng thái");
            System.out.println("6. Thoát");
            int choice = InputValidate.getInt(sc,0,99999,"Lựa chọn của bạn : ");
            if(choice == 6) return;
            switch(choice){
                case 1:
                    findCourse.get().setCourseName(InputValidate.getString(sc,"Nhập tên mới : "));
                    System.out.println("Cập nhật thành công!");
                    break;
                case 2:
                    findCourse.get().setCredit(InputValidate.getInt(sc,0,10,"Nhập số tín chỉ mới : "));
                    System.out.println("Cập nhật thành công!");
                    break;
                case 3:
                    findCourse.get().setTuitionFee(InputValidate.getDouble(sc,0,9999999,"Nhập học phí mới : "));
                    System.out.println("Cập nhật thành công!");
                    break;
                case 4:
                    findCourse.get().setInstructor(InputValidate.getString(sc,"Nhập tên giảng viên phụ trách mới : "));
                    System.out.println("Cập nhật thành công!");
                    break;
                case 5:
                    String status = InputValidate.getString(sc,"Chọn trạng thái mới cho khoá học (True = Y , False = N) : ").toLowerCase();
                    if(status.equals("y") || status.equals("n")){
                        findCourse.get().setStatus(status.equals("y"));
                        System.out.println("Cập nhật thành công!");
                    }else{
                        System.out.println("Giá trị không hợp lệ!Nhập lại!");
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp!");
                    break;
            }
        }

    }

    public void deleteCourse(String id){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        Optional<Course> findCourse = Optional.ofNullable(findById(id));
        if(findCourse.isEmpty()){
            System.out.println("Không tìm thấy khoá học phù hợp!");
            return;
        }
        courseList.remove(findCourse.get());
        System.out.println("Xoá khoá học thành công!");

    }

    public void searchByInstructor(String keyword){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        List<Course> filterInstructor = courseList.stream().filter(v -> v.getInstructor().toLowerCase().contains(keyword.toLowerCase())).toList();
        if(filterInstructor.isEmpty()){
            System.out.println("Không có giảng viên nào phù hợp!");
            return;
        }
        System.out.println("Lọc khoá học theo tên giảng viên thành công!");
        printTableHeader();
        for(Course course : filterInstructor){
            course.displayData();
            System.out.println("+------------------------------------------------------------------------------+");
        }
    }

    public void sortByFeeDesc(){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        List<Course> sortFeeDesc = courseList.stream().sorted(Comparator.comparingDouble(Course::getTuitionFee)).toList();
        if(sortFeeDesc.isEmpty()){
            System.out.println("Không có khoá học nào phù hợp!");
            return;
        }
        printTableHeader();
        for(Course course : sortFeeDesc){
            course.displayData();
            System.out.println("+------------------------------------------------------------------------------+");
        }

    }

    public void filterActiveCourses(){
        if(courseList.isEmpty()){
            System.out.println("Không có phần tử nào trong danh sách!");
            return;
        }
        List<Course> filterActive = courseList.stream().filter(Course::isStatus).toList();
        if(filterActive.isEmpty()){
            System.out.println("Không có khoá học nào phù hợp!");
            return;
        }
        System.out.println("Lọc khoá học theo trạng thái thành công!");
        printTableHeader();
        for(Course course : filterActive){
            course.displayData();
            System.out.println("+------------------------------------------------------------------------------+");
        }
    }

    private void printTableHeader(){
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.printf("|%-10s|%-15s|%-8s|%-15s|%-15s|%-10s|\n","Mã","Tên","Tín chỉ","Học phí","Giảng viên","Trạng thái");
        System.out.println("+------------------------------------------------------------------------------+");
    }


}
