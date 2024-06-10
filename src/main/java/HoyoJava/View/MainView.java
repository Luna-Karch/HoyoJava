package HoyoJava.View;

import HoyoJava.Clients.Client;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainView extends GridPane {
    private Client client;

    public MainView(Client client) {
        super();
        this.client = client;

        Label UIDLabel = new Label(this.client.getUID());

        super.add(UIDLabel, 0, 0);
    }
}
