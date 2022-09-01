package nl.miwgroningen.se.ch9.alex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
//        getPossibleUkeChords(populateChordGrid(chord));
        populateChordGrid(chord);
    }

    private HashMap populateChordGrid(Akkoord chord) {
        int noteIndex = 0;
//        int chordNoteIndex = 0;
        String[] ukuleleStrings = {"G", "C", "E", "A"};
        ArrayList<String> chordNotes = chord.geefTonenInAkkoord();
        ChromaticScale notesList = new ChromaticScale(chord.getToon());
        HashMap<String, HashMap> ukuleleHashMap = new HashMap<>();

        for (int stringIndex = 0; stringIndex < ukuleleStrings.length; stringIndex++) {
            ArrayList<String> stringNotes = selectUkuleleString(ukuleleStrings[stringIndex]);
            HashMap<String, Integer> stringHashMap = new HashMap<>();
            System.out.println();
            for (int chordNoteIndex = 0; chordNoteIndex < chordNotes.size(); chordNoteIndex++) {
                for (String stringNote : stringNotes) {
                    if (stringNote.equals(chordNotes.get(chordNoteIndex))) {
                        stringHashMap.put(chordNotes.get(chordNoteIndex), stringNotes.indexOf(stringNote));
                        System.out.printf("Checking note %s in chord %s for a match on the %s-string. ", chordNotes.get(chordNoteIndex), chord, ukuleleStrings[stringIndex]);
                        System.out.printf("The matching position is: %s\n", stringNotes.indexOf(stringNote));
                        System.out.printf("Hashmap contains: %s\n", stringHashMap);
                    }
                }
                ukuleleHashMap.put(ukuleleStrings[stringIndex], stringHashMap);
            }
            System.out.println("This is the complete set of hashmaps: " + ukuleleHashMap);
        }
        return ukuleleHashMap;
    }

    private ArrayList<String> selectUkuleleString(String note) {
        return new ChromaticScale(note).getNotesList().fillArray();
    }

    private void getPossibleUkeChords(HashMap<String, HashMap> buildingBlocks) {
        HashMap<String, Integer> chordSuggestion = new HashMap<>();
        String[] ukuleleStrings = {"G", "C", "E", "A"};
        String[] chordNotes = {"C", "E", "G"};
        int i = 0;
        int j = 0;
//        chordSuggestion.put("G", 0);
//        chordSuggestion.put("C", 0);
//        chordSuggestion.put("E", 0);
//        chordSuggestion.put("A", 3);
        buildingBlocks.get(ukuleleStrings[i]).get(chordNotes[j]);

        }


    private Boolean isUkeChordValid() {
        return true;
    }

}