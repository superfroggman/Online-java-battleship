package sample;

import javafx.event.ActionEvent;

public class Controller {

    public void buttonPressed(ActionEvent actionEvent) {

        OnlineClient.connectToServer("romland.space", 8545);



    }
}
