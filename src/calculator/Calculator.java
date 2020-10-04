package calculator;

public class Calculator {
    private static int num1 = 0;

    public static void main(String[] args) {

        //show previous results from result file
        //adding this line to test Commit + push for a file - change2
        UtilsClass.showPreviousResults();
        while (true) {
            UtilsClass.printHeader();
            //printResult(calculate(getIntFromUser(), getOperatorFromUser(), getIntFromUser()));
            UtilsClass.printResult(CalculateClass.calculate(UserInteractionClass.getNumFromUser(), UserInteractionClass.getOperatorFromUserUsingInterface(), UserInteractionClass.getNumFromUser()));
        }
    }

}
