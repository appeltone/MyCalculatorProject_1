package calculator;

public class CalculateClass extends Calculator {

    public static double calculate(double num1, Operators operandAction, double num2) {
        double result = 0;
        switch (operandAction) {
            case PLUS: {
                result = (num1 + num2);
                break;
            }
            case MINUS: {
                result = (num1 - num2);
                break;
            }
            case DIVISION: {
                try {
                    result = (num1 / num2);
                } catch (ArithmeticException e) {
                    System.out.println("Dividing by zero is not allowed");
                }
                break;
            }
            case MULTIPLY: {
                result = (num1 * num2);
                break;
            }
        }
        return result;
    }
}
