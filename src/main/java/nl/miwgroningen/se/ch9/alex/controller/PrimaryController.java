package nl.miwgroningen.se.ch9.alex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import nl.miwgroningen.se.ch9.alex.database.AkkoordKeuzeDAO;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;

public class PrimaryController implements Initializable {

    @FXML
    private Label keuzeLabel;
    @FXML
    private Label toonLabel;
    @FXML
    private Label toonsoortLabel;

    @FXML
    private ChoiceBox<String> toonSelectorBox;
    @FXML
    private ChoiceBox<String> toonsoortSelectorBox;

    private String[] tonen = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    private String[] toonsoorten = {"Majeur", "Mineur"};
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
                writeChordToDatabase();
                App.setRoot("secondary");
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toonSelectorBox.getItems().addAll(tonen);
        toonSelectorBox.setOnAction(this::getToon); //"this::" is een "method reference operator"
        toonsoortSelectorBox.getItems().addAll(toonsoorten);
        toonsoortSelectorBox.setOnAction(this::getToonsoort); //"this::" is een "method reference operator"
    }

    private void writeChordToDatabase() {
        DBaccess dBaccess = App.getdBaccess();
        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
        dBaccess.openConnection();
        akkoordKeuzeDAO.slaAkkoordKeuzeOp(akkoord);
        dBaccess.closeConnection();
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
