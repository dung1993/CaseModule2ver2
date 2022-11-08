package src.utils;

import java.text.DecimalFormat;
import java.util.Scanner;

public class AppUtils {
    static Scanner scanner = new Scanner(System.in);

    public static int reChose(int max, int min) {
        int option;
        do {
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option > max || option < min) {
                    System.out.println("Utils invalid! Please your chose:");
                    System.out.print("==>  ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Syntax Error!, Retry Input");
                System.out.print("==>  ");
            }
        } while (true);
        return option;
    }

    public static int retryParseInt() {
        int result;
        do {
            try {
                result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Syntax Error!, Retry Input");
                System.out.print("==>  ");
            }
        } while (true);
    }

    public static Long parseLong() {
        Long result;
        do {
            try {
                result = Long.parseLong(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Syntax Error!, Retry Input");
                System.out.print("==>  ");
            }
        } while (true);
    }



    public static Double parseDouble() {
        Double result;
        do {
            try {
                result = Double.parseDouble(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Syntax Error!, Retry Input");
                System.out.print("==>  ");
            }
        } while (true);
    }



    public static String retryString(String fieldName) {
        String result;
        System.out.println("->  ");
        while ((result = scanner.nextLine()).isEmpty()) {
            System.out.printf("%s,Please not empty\n", fieldName);
            System.out.print("==>  ");
        }
        return result;
    }

    public static String doubleToVND(double value){
        String patternVND = ",###đ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

    public static void exit(){
        System.out.println("Good bye! See you late");
        System.exit(5);
    }
}
