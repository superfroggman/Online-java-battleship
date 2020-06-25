package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

import static sample.OnlineClient.receiveRead;

public class Controller {


    public TextField ipDomainIN;
    public TextField portIN;
    public TextField usernameIN;
    public TextField groupIN;

    public void buttonPressed(ActionEvent actionEvent) {

        OnlineClient.connectToServer(ipDomainIN.getText(), Integer.parseInt(portIN.getText()));

        new Thread(() -> {
            while (true) {
                try {
                    String receiveMessage;
                    if ((receiveMessage = receiveRead.readLine()) != null) {
                        receiveMessage(receiveMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void receiveMessage(String message) {
        System.out.println(message);

        switch (message) {
            case "connectGroup?":
                System.out.println(groupIN.getText());
                OnlineClient.sendMessage(groupIN.getText()); //temp group
                break;
            case "username?":
                OnlineClient.sendMessage(usernameIN.getText()); //temp group
                break;
        }
    }
}
