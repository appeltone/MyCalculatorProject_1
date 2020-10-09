package calculator;

import java.sql.Connection;

public class Calculator {
    private static int num1 = 0;

    public static void main(String[] args) {


        UtilsClass.showPreviousResultsFromDB();
        //UtilsClass.showPreviousResults();
        while (true) {
            UtilsClass.printHeader();
            UtilsClass.printResult(CalculateClass.calculate(UserInteractionClass.getNumFromUser(), UserInteractionClass.getOperatorFromUserUsingInterface(), UserInteractionClass.getNumFromUser()));
        }
    }

}
