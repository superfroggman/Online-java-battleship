package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static sample.OnlineClient.receiveRead;

public class Controller {

    public GridPane connectGroup;
    public TextField ipDomainIN;
    public TextField portIN;
    public TextField usernameIN;
    public TextField groupIN;

    public GridPane gameGroup;
    public GridPane gameGrid;

    @FXML
    public void initialize() {
        gameGroup.setVisible(false);
    }

    long prevTime = 0;

    public void buttonPressed(ActionEvent actionEvent) {

        OnlineClient.connectToServer(ipDomainIN.getText(), Integer.parseInt(portIN.getText()));

        //Get initial time
        final long startNanoTime = System.nanoTime();
        prevTime = startNanoTime;

        // Create Task instance
        Task<Void> task = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                while (true) {
                    try {
                        String receiveMessage;
                        if ((receiveMessage = receiveRead.readLine()) != null) {
                            Platform.runLater(() -> receiveMessage(receiveMessage));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };// Run task in new thread
        new Thread(task).start();

    }

    public void receiveMessage(String message) {
        System.out.println(message);

        switch (message) {
            case "connectGroup?":
                System.out.println(groupIN.getText());
                OnlineClient.sendMessage(groupIN.getText()); //temp group
                break;
            case "username?":
                OnlineClient.sendMessage(String.valueOf(Math.random())); //temp group
                break;
            case "usernameSet!":
                game = new Battleship();
                renderGrid();

                connectGroup.setVisible(false);
                gameGroup.setVisible(true);

                break;
        }
    }

    Battleship game;

    public void renderGrid() {
        System.out.println("rendering grid");
        for (int i = 0; i < game.cells.size() - 1; i++) {
            for (int j = 0; j < game.cells.get(j).size() - 1; j++) {
                Rectangle rect = new Rectangle();

                rect.setFill(Color.AQUA);
                rect.setWidth(20);
                rect.setHeight(20);

                gameGrid.add(rect, i, j);
                game.cells.get(i).get(j);
            }
        }
    }
}
