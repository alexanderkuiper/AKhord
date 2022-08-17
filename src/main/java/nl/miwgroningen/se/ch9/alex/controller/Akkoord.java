package nl.miwgroningen.se.ch9.alex.controller;

import nl.miwgroningen.se.ch9.alex.model.CircularLinkedList;

import java.util.Arrays;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Klasse voor akkoord: een toon en toonsoort.
 */
public class Akkoord {
    private static final String[] tonen = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    private static final String[] toonsoorten = {"Majeur", "Mineur"};


    private String toon;
    private String toonsoort;

    public Akkoord(String toon, String toonsoort) {
        this.toon = toon;
        this.toonsoort = toonsoort;
    }

    // TODO: implement this function
    public String[] geefTonenInAkkoord() {
        String[] chord = new String[2];
        // detect the index of the chosen note
        int toonIndex = 0;
        for (int i = 0; i < tonen.length; i++) {
            if (toon.equals(tonen[i])) {
                toonIndex = i + 1;
            }
        }
        // fill the list
        CircularLinkedList tonenLijst = new CircularLinkedList();
        for (String toon : tonen) {
            tonenLijst.addNode(toon);
        }
        // set the chosen note as the root note of the circular linked list
        tonenLijst.setHead(toonIndex);
        // return the chord depending on the mode
        switch (toonsoort) {
            case "Majeur":
                return geefMajeur(tonenLijst);
//                chord = Arrays.copyOf(geefMajeur(tonenLijst), 3);
            case "Mineur":
                return geefMineur(tonenLijst);
            default:
                return chord;
        }
//        tonenLijst.display();
    }

    private String[] geefMineur(CircularLinkedList tonenLijst) {
        String[] chord = new String[12];
////        tonenLijst.toArray(chord);
//        System.out.println(Arrays.toString(chord));
//        String[] result = {chord[0], chord[4], chord[7]};
        String[] result = new String[3];
        for (int i = 0; i < tonenLijst.size(); i++) {
            chord[i] = tonenLijst.get(i);
        }
        return result;
    }

    private String[] geefMajeur(CircularLinkedList tonenLijst) {
        System.out.println("tonenlijst size: " + tonenLijst.size());
        String[] chord = new String[12];
        tonenLijst.toArray(chord);
        String[] result = {chord[0], chord[5], chord[7]};
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.toon, this.toonsoort);
    }

    public String getToon() {
        return toon;
    }

    public String getToonsoort() {
        return toonsoort;
    }

    public void setToon(String toon) {
        this.toon = toon;
    }

    public void setToonsoort(String toonsoort) {
        this.toonsoort = toonsoort;
    }

    public static String[] getTonen() {
        return tonen;
    }

    public static String[] getToonsoorten() {
        return toonsoorten;
    }
}

