package edu.pio.chinczyk;

import edu.pio.chinczyk.game.Board;
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
    private final HashMap<Scene, RootController> controllers;
    private Stage stage = null;
    private final Board board;

    public LudoApp() {
        scenes = new HashMap<>();
        controllers = new HashMap<>();
        board = new Board();

        loadScenes();
    }

    private void loadScenes() {
        for (String path : LudoApp.scenePaths) {
            URL sceneURL = LudoApp.class.getResource(path);
            if (sceneURL == null) {
                continue;
            }

            try {
                FXMLLoader loader = new FXMLLoader(sceneURL);
                Scene scene = new Scene(loader.load());

                RootController controller = loader.getController();
                controller.setApp(this);

                scenes.put(path, scene);
                controllers.put(scene, controller);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene(String path) {
        return scenes.getOrDefault(path, null);
    }

    public RootController getController(Scene scene) {
        return controllers.get(scene);
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        for(RootController controller : controllers.values())
            controller.runBeforeStart();

        stage.setTitle("CHINCZYK!");
        stage.setScene(this.getScene("menu.fxml"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}