package nl.miwgroningen.se.ch9.alex.controller;

//import /java.io.IOException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import nl.miwgroningen.se.ch9.alex.model.Akkoord;
import nl.miwgroningen.se.ch9.alex.model.ChromaticScale;

public class PrimaryController implements Initializable {

    private static final int NUMBER_OF_HISTORY = 5;
    @FXML
    private Label keuzeLabel;
    @FXML
    private Label toonLabel;
    @FXML
    private Label toonsoortLabel;
    @FXML
    private Button akkoord1;
    @FXML
    private Button akkoord2;
    @FXML
    private Button akkoord3;
    @FXML
    private Button akkoord4;
    @FXML
    private Button akkoord5;


    @FXML
    private ChoiceBox<String> toonSelectorBox;
    @FXML
    private ChoiceBox<String> toonsoortSelectorBox;

    private String mijnToon = "";
    private String mijnToonsoort = "";
    private Akkoord akkoord = new Akkoord(mijnToon, mijnToonsoort);

    @FXML
    private void submitUserChoice() throws IOException {
        try {
            if (akkoord.getToon().isEmpty()) {
                // maak Selecteer een toon rood
                toonLabel.setTextFill(Color.color(1, 0, 0));
            } else if (akkoord.getToonsoort().isEmpty()) {
                // maak Selecteer een toonsoort rood
                toonsoortLabel.setTextFill(Color.color(1, 0, 0));
            } else {
                Akkoord.writeChordToDatabase(akkoord);
                App.setRoot("secondary");
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @FXML
    private void setChordHistory() {
        Button[] buttons = {akkoord1, akkoord2, akkoord3, akkoord4, akkoord5};
        for (int chordPosition = 0; chordPosition < NUMBER_OF_HISTORY; chordPosition++) {
            Akkoord historyChord = Akkoord.pullChordFromDatabase(chordPosition + 1, true);
            if (historyChord != null) {
                buttons[chordPosition].setVisible(true);
                buttons[chordPosition].setText(historyChord.getToon() + " " + historyChord.getToonsoort());
            }
        }
    }



    // TODO: implement function that shows the history chord
    @FXML
    private void handleButtoneOne() {
        clickHistoryButton(1);
    }
    @FXML
    private void handleButtonTwo() {
        clickHistoryButton(2);
    }
    @FXML
    private void handleButtonThree() {
        clickHistoryButton(3);
    }
    @FXML
    private void handleButtonFour() {
        clickHistoryButton(4    );
    }
    @FXML
    private void handleButtonFive() {
        clickHistoryButton(5);
    }
    private void clickHistoryButton(int buttonNumber) {
        try {
            akkoord = Akkoord.pullChordFromDatabase(buttonNumber, true);
            Akkoord.writeChordToDatabase(akkoord);
            App.setRoot("secondary");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toonSelectorBox.getItems().addAll(ChromaticScale.getNotes());
        toonSelectorBox.setOnAction(this::getToon); //"this::" is een "method reference operator"
        toonsoortSelectorBox.getItems().addAll(Akkoord.getToonsoorten());
        toonsoortSelectorBox.setOnAction(this::getToonsoort); //"this::" is een "method reference operator"
        setChordHistory();
    }

    // geeft de toonkeuze van de gebruiker weer en slaat deze op (TODO)
    private void getToon(ActionEvent event) {
        mijnToon = toonSelectorBox.getValue();
        akkoord.setToon(mijnToon);
        keuzeLabel.setText("Gekozen akkoord: " + akkoord.toString());
    }

    // geeft de toonsoortkeuze van de gebruiker weer en slaat deze op (TODO)
    private void getToonsoort(ActionEvent event) {
        mijnToonsoort = toonsoortSelectorBox.getValue();
        akkoord.setToonsoort(mijnToonsoort);
        keuzeLabel.setText("Gekozen akkoord: " + akkoord.toString());
    }

}
