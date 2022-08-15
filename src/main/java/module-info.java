module nl.miwgroningen.se.ch9.alex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens nl.miwgroningen.se.ch9.alex to javafx.fxml;
//    exports nl.miwgroningen.se.ch9.alex;
    exports nl.miwgroningen.se.ch9.alex.controller;
    opens nl.miwgroningen.se.ch9.alex.controller to javafx.fxml;
}
