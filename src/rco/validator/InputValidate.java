package rco.validator;

import java.util.Scanner;

public class InputValidate {
    public static String getString(Scanner sc,String label){
        while(true){
            System.out.print(label);
            String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Không được để trống ô nhập!Nhập lại!");
            }else{
                return input.trim();
            }
        }
    }

    public static int getInt(Scanner sc,int min,int max,String label){
        while(true){
            try{
                System.out.print(label);
                int input = Integer.parseInt(sc.nextLine());
                if(input<min || input>max){
                    System.out.println("Giá trị không được nhỏ hơn "+min+" hoặc lớn hơn "+max+"!Nhập lại!");
                }else {
                    return input;
                }
            }catch(NumberFormatException e){
                System.out.println("Bạn cần nhập số!");
            }
        }
    }

    public static double getDouble(Scanner sc,int min,int max,String label){
        while(true){
            try{
                System.out.print(label);
                double input = Double.parseDouble(sc.nextLine());
                if(input<min || input>max){
                    System.out.println("Giá trị không được nhỏ hơn "+min+" hoặc lớn hơn "+max+"!Nhập lại!");
                }else {
                    return input;
                }
            }catch(NumberFormatException e){
                System.out.println("Bạn cần nhập số!");
            }
        }
    }
}
