package nl.miwgroningen.se.ch9.alex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nl.miwgroningen.se.ch9.alex.controller.App;
import nl.miwgroningen.se.ch9.alex.database.AkkoordKeuzeDAO;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;

public class SecondaryController implements Initializable {

    private Akkoord gekozenAkkoord;

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
        pullChordFromDatabase();
        akkoordLabel.setText(gekozenAkkoord.toString());
        notenLabel.setText(Arrays.toString(gekozenAkkoord.geefTonenInAkkoord()));
    }



    private void pullChordFromDatabase() {
        DBaccess dBaccess = App.getdBaccess();
        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
        dBaccess.openConnection();

        gekozenAkkoord = akkoordKeuzeDAO.toonAkkoorden().get(akkoordKeuzeDAO.toonAkkoorden().size() - 1);

        dBaccess.closeConnection();
    }

}