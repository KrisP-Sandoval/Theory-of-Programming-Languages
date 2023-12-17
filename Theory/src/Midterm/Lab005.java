package Midterm;
import java.util.*;

public class Lab005 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Expression: ");
        String expression = scanner.nextLine();

        String result = analyzeExpression(expression);
        System.out.println(result);
    }

    private static String analyzeExpression(String expression) {
        String pattern = "(\\w+)\\s+(\\w+)\\s*=\\s*([^;]+);";
        if (expression.matches(pattern)) {
            String[] parts = expression.split("\\s*=\\s*|\\s+");
            String variableName = parts[1];
            String dataType = parts[0];
            String value = parts[2].replace(";", "").trim();

            if (dataType.equals("int")) {
                if (!value.matches("\\d+")) {
                    return "Semantically Incorrect! The assigned value should be an integer for int type.";
                }
            } else if (dataType.equals("double")) {
                try {
                    Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    return "Semantically Incorrect! The assigned value should be a numeric for double type.";
                }
            } else if (dataType.equals("String")) {
                value = value.trim();
                if (!value.startsWith("\"") || !value.endsWith("\"")) {
                    return "Semantically Incorrect! The assigned value should be a string for String type.";
                }
            } else {
                return "Semantically Incorrect! Unknown data type.";
            }

            return "Semantically Correct!";
        } else {
            return "Invalid expression format.";
        }
    }
}
