package edu.pio.chinczyk;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

import static edu.pio.chinczyk.LudoApp.DICE_FXML_FILE;

public class DiceController extends ImageView {
    private int rollResult;
    private final Random random = new Random();
    private boolean waitingForRoll = false;

    public DiceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(DICE_FXML_FILE));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            if(waitingForRoll) {
                rollResult = random.nextInt(6) + 1;
                this.setImage(new Image(getClass().getResource("img/dice" + rollResult + ".png").toString()));
                waitingForRoll = false;
            }
            else {
                mouseEvent.consume();
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

