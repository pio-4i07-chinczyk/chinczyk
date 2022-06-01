package edu.pio.chinczyk;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class RootController {
    private LudoApp app;

    public void setApp(LudoApp app) {
        this.app = app;
    }

    public LudoApp getApp() {
        return this.app;
    }

    public final void route(String name) {
        Stage stage = app.getStage();

        Scene current = stage.getScene();
        RootController currentController = app.getController(current);

        Scene next = app.getScene(name);
        RootController nextController = app.getController(next);

        nextController.runBeforeRoute(currentController);

        stage.setScene(next);
        stage.show();
    }

    public void runBeforeStart() {}

    public void runBeforeRoute(RootController previous) {}
}
