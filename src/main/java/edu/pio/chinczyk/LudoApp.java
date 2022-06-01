package edu.pio.chinczyk;

import edu.pio.chinczyk.game.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;

public class LudoApp extends Application {
    public static final String MENU_FXML_FILE = "menu.fxml";
    public static final String RULES_FXML_FILE = "rules.fxml";
    public static final String GAME_SELECTOR_FXML_FILE = "game-selector.fxml";
    public static final String GAME_FXML_FILE = "game.fxml";
    public static final String DICE_FXML_FILE = "dice.fxml";

    public static final String GAME_TITLE = "CHINCZYK";

    private static final String[] scenePaths = {
            MENU_FXML_FILE,
            RULES_FXML_FILE,
            GAME_SELECTOR_FXML_FILE,
            GAME_FXML_FILE
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

        for(RootController controller : controllers.values()) {
            controller.runBeforeStart();
        }

        stage.setTitle(GAME_TITLE);
        stage.setScene(this.getScene(MENU_FXML_FILE));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}