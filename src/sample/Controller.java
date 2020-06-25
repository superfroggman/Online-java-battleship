package sample;

import javafx.event.ActionEvent;

public class Controller {

    public void buttonPressed(ActionEvent actionEvent) {

        OnlineClient.connectToServer("localhost", 8545);


    }

    public static void receiveMessage(String message) {
        System.out.println(message);

        switch (message){
            case "connectGroup?":
                OnlineClient.sendMessage("battleship"); //temp group
                break;
            case "username?":
                OnlineClient.sendMessage("battleship-player"); //temp group
                break;
        }
    }
}
