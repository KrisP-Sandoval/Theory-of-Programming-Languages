import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        // Perform Lexical Analysis (Replace this with your actual logic)
        String result = "Lexical Analysis Phase Passed";
        resultTextArea.setText(result);

        // Enable/disable buttons accordingly
        lexicalButton.setDisable(true);
        syntaxButton.setDisable(false);
        semanticButton.setDisable(true);
    }

    private void syntaxAnalysis() {
        // Perform Syntax Analysis (Replace this with your actual logic)
        String result = "Syntax Analysis Phase Passed";
        resultTextArea.setText(result);

        // Enable/disable buttons accordingly
        lexicalButton.setDisable(true);
        syntaxButton.setDisable(true);
        semanticButton.setDisable(false);
    }

    private void semanticAnalysis() {
        // Perform Semantic Analysis (Replace this with your actual logic)
        String result = "Semantic Analysis Phase Passed";
        resultTextArea.setText(result);

        // Enable/disable buttons accordingly
        lexicalButton.setDisable(true);
        syntaxButton.setDisable(true);
        semanticButton.setDisable(true);
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