import java.util.Stack;

public class RPNCalculator {

    public static double evaluate(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                double result = applyOperation(a, b, token);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double applyOperation(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }

    public static void main(String[] args) {
        String expression = "3 4 + 2 * 7 /"; //  ((3 + 4) * 2) / 7
        double result = evaluate(expression);
        System.out.println("Результат: " + result);
    }
}

