package HoyoJava;

import javafx.scene.Scene;
import javafx.stage.Stage;
import HoyoJava.View.Entrance;
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

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Testing!");
        stage.setResizable(false);

        Entrance entranceScreen = new Entrance();
        
        stage.setScene(new Scene(entranceScreen, 188, 150));
        stage.show();
    }
}