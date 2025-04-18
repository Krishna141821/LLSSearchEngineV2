import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LLSVisualizer extends Application {

    private LLSSearchEngineV2 engine;
    private TextArea displayArea;

    @Override
    public void start(Stage primaryStage) {
        engine = new LLSSearchEngineV2(new int[]{58, 192, 70, 8, 126, 774});

        TextField inputField = new TextField();
        inputField.setPromptText("Enter number");

        Button insertBtn = new Button("Insert");
        Button deleteBtn = new Button("Delete");
        Button searchBtn = new Button("Search");

        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(300);

        insertBtn.setOnAction(e -> {
            int num = Integer.parseInt(inputField.getText());
            engine.insert(num);
            updateDisplay("Inserted " + num);
        });

        deleteBtn.setOnAction(e -> {
            int num = Integer.parseInt(inputField.getText());
            engine.delete(num);
            updateDisplay("Deleted " + num);
        });

        searchBtn.setOnAction(e -> {
            int num = Integer.parseInt(inputField.getText());
            boolean found = engine.search(num);
            updateDisplay("Search " + num + ": " + (found ? "Found ✅" : "Not Found ❌"));
        });

        HBox controls = new HBox(10, inputField, insertBtn, deleteBtn, searchBtn);
        VBox layout = new VBox(10, controls, displayArea);
        layout.setStyle("-fx-padding: 10px");

        primaryStage.setTitle("LLSSearchEngineV2 Visualizer");
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();

        updateDisplay("Initialized engine.");
    }

    private void updateDisplay(String message) {
        StringBuilder sb = new StringBuilder(message + "\n\nLevels:\n");
        for (var entry : engine.getLevels().entrySet()) {
            sb.append("Level ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
