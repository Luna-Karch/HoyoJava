package HoyoJava.View;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

public class EntranceView extends GridPane {
    private MainView mainView = new MainView();

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

            // Send Data to MainView
        }
    }

    public EntranceView() {
        super();

        super.setStyle("-fx-background-color: #474747");
        super.setAlignment(Pos.CENTER);

        TextField inputID = new TextField();
        Label inputLabel = new Label("Enter UID:");
        Button inputButton = new Button("Fetch");
        Label checkLabel = new Label();

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

        super.setVgap(10);

        super.add(inputLabel, 0, 0);
        super.add(inputID, 0, 1);
        super.add(inputButton, 0, 2);
        super.add(checkLabel, 0, 3);
    }

    public MainView getMainView() { return this.mainView; }
}
