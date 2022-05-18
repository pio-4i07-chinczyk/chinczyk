module edu.pio.chinczyk {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.pio.chinczyk to javafx.fxml;
    exports edu.pio.chinczyk;
}