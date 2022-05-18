package edu.pio.chinczyk;

public class RootController {
    private Object app;

    public void setApp(Object app) {
        this.app = app;
    }

    protected Object getApp() {
        return this.app;
    }
}
