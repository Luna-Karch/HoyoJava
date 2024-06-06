package HoyoJava;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.application.Application;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private boolean sanitziedInput(String input) {
        if (input.length() == 0) {
            return false;
        }

        for (char c: input.toCharArray()) {
            if (!(Character.isDigit(c))) {
                return false;
            }
        }

        return true;
    }

    private void processInput(String input, Label output) {
        System.out.println(input + ": " + sanitziedInput(input));

        if (!sanitziedInput(input)) {
            output.setText("That is not your UID.");
            output.setTextFill(Color.RED);
        } else {
            output.setText("Attempting to fetch data...");
            output.setTextFill(Color.WHITE);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Testing!");
        stage.setResizable(false);

        GridPane grid = new GridPane();
        TextField inputID = new TextField();
        Label inputLabel = new Label("Enter UID:");
        Button inputButton = new Button("Fetch");
        Label checkLabel = new Label();

        grid.setStyle("-fx-background-color: #474747");
        grid.setAlignment(Pos.CENTER);

        inputLabel.setTextFill(Color.WHITE);

        inputButton.setMaxWidth(Double.MAX_VALUE);

        inputButton.setOnAction(event -> {
            processInput(inputID.getText(), checkLabel);
        });
    
        inputID.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                processInput(inputID.getText(), checkLabel);
            }
        });

        inputButton.setOnAction(event -> {
            processInput(inputID.getText(), checkLabel);
        });

        grid.setVgap(10);
        grid.add(inputLabel, 0, 0);
        grid.add(inputID, 0, 1);
        grid.add(inputButton, 0, 2);
        grid.add(checkLabel, 0, 3);
        
        stage.setScene(new Scene(grid, 188, 150));
        stage.show();
    }
}