package edu.pio.chinczyk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;

public class LudoApp extends Application {
    private static final String[] scenePaths = {
            "menu.fxml",
            "rules.fxml",
            "game-selector.fxml",
            "game.fxml"
    };

    private final HashMap<String, Scene> scenes;
    private Stage stage = null;

    public LudoApp() {
        scenes = new HashMap<>();
        loadScenes();
    }

    private void loadScenes() {

        for (String path : LudoApp.scenePaths) {
            URL sceneURL = LudoApp.class.getResource(path);
            if (sceneURL == null) {
                continue;
            }

            try {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(sceneURL);
                Scene scene = new Scene(loader.load());
                scenes.put(path, scene);

                RootController appInjectable = (RootController) loader.getController();
                appInjectable.setApp(this);
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
        stage.setScene(this.getScene("menu.fxml"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}