package nl.miwgroningen.se.ch9.alex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nl.miwgroningen.se.ch9.alex.model.Akkoord;


public class SecondaryController implements Initializable {

//    private static Akkoord gekozenAkkoord;

    @FXML
    private Label akkoordLabel;
    @FXML
    private Label notenLabel;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // chordPosition of 1 is the latest entry
        int chordPosition = 1;
        akkoordLabel.setText(Akkoord.pullChordFromDatabase(chordPosition, false).toString());
        notenLabel.setText(Akkoord.pullChordFromDatabase(chordPosition,false).geefTonenInAkkoord().toString());

    }

}