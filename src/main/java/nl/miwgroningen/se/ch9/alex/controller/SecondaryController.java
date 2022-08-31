package nl.miwgroningen.se.ch9.alex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nl.miwgroningen.se.ch9.alex.model.Akkoord;
import nl.miwgroningen.se.ch9.alex.model.ChromaticScale;


public class SecondaryController implements Initializable {
    // chordPosition of 1 is the latest database entry
    private static final int CHORD_POSITION = 1;

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
        Akkoord chord = Akkoord.pullChordFromDatabase(CHORD_POSITION, false);
        akkoordLabel.setText(chord.toString());
        notenLabel.setText(chord.geefTonenInAkkoord().toString());
        populateChordGrid(chord);

    }

    private void populateChordGrid(Akkoord chord) {
        ChromaticScale notesList = new ChromaticScale(chord.getToon());
        // for each note in chord:
        // if (note equals noteslist[i]) {
        // display X on the desired coordinate }
        // else (increment i and display x on its coordinate

    }

}