package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteractionClass {

    public static double getNumFromUser() {
        double numFromUser = 0;
        boolean validNum;
        double num1 = 0;

        validNum = false; //reset for next iteration
        while (validNum == false) {//must be true inorder to stop - will loop untill given number is a number
            try {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("Please enter the first operand (number only): ");
                num1 = scan1.nextInt();//get the number from user
                if (num1 == -1) {//this if is only for an exit code out of the program
                    System.out.println("CALCULATOR WILL EXIT NOW, BYE BYE.....");
                    System.exit(0); //command for exiting program
                }
                validNum = true; //if no exception the number is valid
            } catch (InputMismatchException ex) {
                System.out.println(ex + "\nMust enter a number!! please try again:");

            }
        }
        return num1;
    }

    public static Operators getOperatorFromUserUsingInterface() {
        int flag = 0;
        char operator;
        Operators enumOperation = null;

        Scanner scan2 = new Scanner(System.in);
        System.out.println("Please enter the operator ( - , + , / , *): ");
        flag = 0; // need this to reset the flag for next iteration
        while (flag == 0) {
            operator = scan2.next().charAt(0);//get the operand from user
            switch (operator) {
                case '+': {
                    enumOperation = Operators.PLUS;
                    flag = 1;
                    break;
                }
                case '-': {
                    enumOperation = Operators.MINUS;
                    flag = 1;
                    break;
                }
                case '/': {
                    enumOperation = Operators.DIVISION;
                   flag = 1;
                    break;
                }
                case '*': {
                    enumOperation = Operators.MULTIPLY;
                    flag = 1;
                    break;
                }
                default: {
                    System.out.println("please try again, only operand");
                }
            }
        }
        return enumOperation;
    }


}
