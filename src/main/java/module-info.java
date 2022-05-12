module edu.pio.chinczyk.chinczyk {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.pio.chinczyk.chinczyk to javafx.fxml;
    exports edu.pio.chinczyk.chinczyk;
}