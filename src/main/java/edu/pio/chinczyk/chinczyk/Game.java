package edu.pio.chinczyk.chinczyk;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;

public class Game extends javafx.application.Application {
    private final HashMap<String, Scene> scenes;
    private Stage stage = null;

    public Game() {
        scenes = new HashMap<>();

        String[] scenePaths = {
            "hello-view.fxml",
            "rulesScene.fxml"
        };

        for(String path : scenePaths) {
            URL sceneURL = Game.class.getResource(path);
            if(sceneURL == null) {
                continue;
            }

            try {
                Scene scene = new Scene(FXMLLoader.load(sceneURL));
                scene.setUserData(this);
                scenes.put(path, scene);
            }
            catch (Exception ignore) {
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene(String path) {
        return scenes.getOrDefault(path, null);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("CHINCZYK!");
        stage.setScene(this.getScene("hello-view.fxml"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}