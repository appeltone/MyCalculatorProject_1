package calculator;

import java.sql.Connection;

public class Calculator {
    private static int num1 = 0;

    public static void main(String[] args) {


        UtilsClass.showPreviousResultsFromDB();
        //UtilsClass.showPreviousResults();
        // todo remove this while loop and implement a different logic (I want you to show another way)
        //   break line 17 into 4 lines to first get the arguments from the user and then call calculate
        while (true) {
            UtilsClass.printHeader();
            UtilsClass.printResult(CalculateClass.calculate(UserInteractionClass.getNumFromUser(), UserInteractionClass.getOperatorFromUserUsingInterface(), UserInteractionClass.getNumFromUser()));
        }
    }

}
