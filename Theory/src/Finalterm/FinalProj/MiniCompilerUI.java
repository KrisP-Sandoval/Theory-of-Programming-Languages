package Finalterm.FinalProj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniCompilerUI extends Application {

    private TextArea codeTextArea;
    private Button openButton, lexicalButton, syntaxButton, semanticButton, clearButton;
    private TextArea resultTextArea;
    private String fileContents = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mini Compiler");

        codeTextArea = new TextArea();
        codeTextArea.setPrefColumnCount(50);
        codeTextArea.setPrefRowCount(10);

        openButton = new Button("Open File");
        openButton.setOnAction(e -> openFile());

        lexicalButton = new Button("Lexical Analysis");
        lexicalButton.setDisable(true);
        lexicalButton.setOnAction(e -> lexicalAnalysis());

        syntaxButton = new Button("Syntax Analysis");
        syntaxButton.setDisable(true);
        syntaxButton.setOnAction(e -> syntaxAnalysis());

        semanticButton = new Button("Semantic Analysis");
        semanticButton.setDisable(true);
        semanticButton.setOnAction(e -> semanticAnalysis());

        clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clear());

        resultTextArea = new TextArea();
        resultTextArea.setPrefColumnCount(50);
        resultTextArea.setPrefRowCount(2);
        resultTextArea.setEditable(false);
        resultTextArea.setDisable(true);

        VBox vbox = new VBox(codeTextArea, openButton, lexicalButton, syntaxButton, semanticButton, clearButton,
                resultTextArea);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                fileContents = Files.readString(Path.of(selectedFile.getAbsolutePath()));
                codeTextArea.setText(fileContents);

                // Enable buttons
                lexicalButton.setDisable(false);
                syntaxButton.setDisable(true);
                semanticButton.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void lexicalAnalysis() {
        List<String> tokens = new ArrayList<>();

        // Implement your compiler logic for Lexical Analysis here
        String[] lexemes = codeTextArea.getText().split("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)\\s+");

        List<String> expectedTokens = Arrays.asList("<data_type>", "<assignment_operator>", "<delimiter>", "<value>", "<identifier>");

        for (String lexeme : lexemes) {
            if (lexeme.matches("int|double|char|String")) {
                tokens.add("<data_type>");
            } else if (lexeme.equals("=")) {
                tokens.add("<assignment_operator>");
            } else if (lexeme.endsWith(";")) {
                String value = lexeme.substring(0, lexeme.length() - 1);
                if (isString(value)) {
                    tokens.add("<value>");
                } else if (isCharacter(value)) {
                    tokens.add("<value>");
                } else if (isNumeric(value)) {
                    tokens.add("<value>");
                }
                tokens.add("<delimiter>");
            } else if (isString(lexeme)) {
                tokens.add("<value>");
            } else if (isCharacter(lexeme)) {
                tokens.add("<value>");
            } else if (isNumeric(lexeme)) {
                tokens.add("<value>");
            } else if (lexeme.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                tokens.add("<identifier>");
            }
        }

        // Display the result in the result text area
        resultTextArea.setText("Lexical Analysis Success:\n" + String.join(" ", tokens));

        // Enable/disable buttons accordingly
        lexicalButton.setDisable(true);
        syntaxButton.setDisable(false);
        semanticButton.setDisable(true);

        if (!tokens.containsAll(expectedTokens) || !expectedTokens.containsAll(tokens)) {
            // Display the error outcome
            resultTextArea.setText("Lexical Analysis Error:\n");

            // Enable/disable buttons accordingly
            lexicalButton.setDisable(true);
            syntaxButton.setDisable(true);
            semanticButton.setDisable(true);
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

    private void syntaxAnalysis() {
        try {
            String code = codeTextArea.getText();

            // Define a pattern for a simple assignment statement
            Pattern assignmentPattern = Pattern.compile("\\b([a-zA-Z][a-zA-Z0-9_]*)\\s*=\\s*([^;]+)\\s*;\\s*");

            // Match the pattern against the entire code
            Matcher matcher = assignmentPattern.matcher(code);

            if (matcher.find()) {
                // Extract matched groups
                String variable = matcher.group(1);
                String value = matcher.group(2);

                // Display the result in the result text area
                resultTextArea.setText("Syntax Analysis Success:\nVariable: " + variable + "\nValue: " + value);

                // Enable/disable buttons accordingly
                lexicalButton.setDisable(true);
                syntaxButton.setDisable(true);
                semanticButton.setDisable(false);
            } else {
                throw new Exception("Syntax error: Invalid assignment statement");
            }
        } catch (Exception e) {
            // Display the error outcome
            resultTextArea.setText("Syntax Analysis Error:\n" + e.getMessage());

            // Enable/disable buttons accordingly
            lexicalButton.setDisable(true);
            syntaxButton.setDisable(true);
            semanticButton.setDisable(true);
        }
    }

    private void semanticAnalysis() {
        try {
            String expression = codeTextArea.getText();
            String result = analyzeExpression(expression);
            resultTextArea.setText("Semantic Analysis: " + result);

            lexicalButton.setDisable(true);
            syntaxButton.setDisable(true);
            semanticButton.setDisable(true);
        } catch (Exception e) {
            resultTextArea.setText("Semantic Analysis Error:\n" + e.getMessage());

            // Enable/disable buttons accordingly
            lexicalButton.setDisable(true);
            syntaxButton.setDisable(true);
            semanticButton.setDisable(true);
        }
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

    private void clear() {
        // Reset everything
        codeTextArea.clear();
        resultTextArea.clear();

        // Disable buttons
        lexicalButton.setDisable(true);
        syntaxButton.setDisable(true);
        semanticButton.setDisable(true);
    }
}