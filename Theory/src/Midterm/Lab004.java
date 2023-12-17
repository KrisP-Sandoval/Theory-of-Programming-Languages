package Midterm;
import java.util.Scanner;

public class Lab004 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Source Language: ");
        String input = scanner.nextLine();

        tokenize(input);

        scanner.close();
    }

    private static void tokenize(String input) {
        String[] lexemes = input.split("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)\\s+");

        for (String lexeme : lexemes) {
            if (lexeme.matches("int|double|char|String")) {
                System.out.print("<data_type> ");
            } else if (lexeme.equals("=")) {
                System.out.print("<assignment_operator> ");
            } else if (lexeme.endsWith(";")) {
                String value = lexeme.substring(0, lexeme.length() - 1);
                if (isString(value)) {
                    System.out.print("<value> ");
                } else if (isCharacter(value)) {
                    System.out.print("<value> ");
                } else if (isNumeric(value)) {
                    System.out.print("<value> ");
                }
                System.out.print("<delimiter> ");
            } else if (isString(lexeme)) {
                System.out.print("<value> ");
            } else if (isCharacter(lexeme)) {
                System.out.print("<value> ");
            } else if (isNumeric(lexeme)) {
                System.out.print("<value> ");
            } else if (lexeme.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                System.out.print("<identifier> ");
            }
        }
    }

    private static boolean isString(String lexeme) {
        return lexeme.matches("\"[^\"]*\"");
    }

    private static boolean isCharacter(String lexeme) {
        return lexeme.matches("'.'");
    }

    private static boolean isNumeric(String lexeme) {
        return lexeme.matches("\\d+");
    }
}
