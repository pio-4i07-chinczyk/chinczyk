package edu.pio.chinczyk.chinczyk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class DiceController extends ImageView {
    @FXML
    private ImageView dice;

    private int roll_result;
    private final Random random = new Random();

    public DiceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dice.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public int getRoll_result() {
        return roll_result;
    }

    @FXML
    protected void onMouseClick(final MouseEvent event) throws IOException {
        roll_result = random.nextInt(6) + 1;
        dice.setImage(new Image(getClass().getResource("img/dice" + roll_result + ".png").toString()));
    }
}

