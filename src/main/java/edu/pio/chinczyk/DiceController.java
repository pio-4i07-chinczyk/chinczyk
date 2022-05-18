package edu.pio.chinczyk;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

public class DiceController extends ImageView {
    @FXML
    private ImageView dice;

    private int rollResult;
    private final Random random = new Random();
    private boolean waitingForRoll = false;

    public DiceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dice.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(waitingForRoll) {
                    rollResult = random.nextInt(6) + 1;
                    dice.setImage(new Image(getClass().getResource("img/dice" + rollResult + ".png").toString()));
                }
                else {
                    mouseEvent.consume();
                }
            }
        });
    }

    public int getRollResult() {
        return rollResult;
    }

    public void setWaitingForRoll(boolean waitingForRoll) {
        this.waitingForRoll = waitingForRoll;
    }
}

