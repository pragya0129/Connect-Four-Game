module com.pragya.connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pragya.connect4 to javafx.fxml;
    exports com.pragya.connect4;
}