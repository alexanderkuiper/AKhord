package nl.miwgroningen.se.ch9.alex.controller;

import nl.miwgroningen.se.ch9.alex.database.AkkoordKeuzeDAO;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;
import nl.miwgroningen.se.ch9.alex.model.ChromaticScale;
import nl.miwgroningen.se.ch9.alex.model.CircularLinkedList;

import java.util.ArrayList;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Klasse voor akkoord: een toon en toonsoort.
 */
public class Akkoord {
    private static final String[] toonsoorten = {"Majeur", "Mineur"};

    private static ArrayList<Integer> toonsoortInterval = new ArrayList<>();

    private String toon;
    private String toonsoort;

    public Akkoord(String toon, String toonsoort) {
        this.toon = toon;
        this.toonsoort = toonsoort;
    }

    public ArrayList<String> geefTonenInAkkoord() {
        // Set the base of the chord interval to major
        toonsoortInterval.add(0, 0);
        toonsoortInterval.add(1, 4);
        toonsoortInterval.add(2, 7);

        ChromaticScale scale = new ChromaticScale(toon);
        CircularLinkedList tonenLijst = scale.getNotesList();
        switch (toonsoort) {
            case "Majeur":
                return geefAkkoord(tonenLijst);
            case "Mineur":
                toonsoortInterval.set(1, 3);
                return geefAkkoord(tonenLijst);
            default:
                return null;
        }
    }

    private ArrayList<String> geefAkkoord(CircularLinkedList tonenLijst) {
        ArrayList<String> chord = tonenLijst.fillArray();
        ArrayList<String> result = new ArrayList<>();
        result.add(chord.get(toonsoortInterval.get(0)));
        result.add(chord.get(toonsoortInterval.get(1)));
        result.add(chord.get(toonsoortInterval.get(2)));
        return result;
    }

    public static void writeChordToDatabase(Akkoord mp_akkoord) {
        DBaccess dBaccess = App.getdBaccess();
        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
        dBaccess.openConnection();
        akkoordKeuzeDAO.slaAkkoordKeuzeOp(mp_akkoord);
        dBaccess.closeConnection();
    }

    public static Akkoord pullChordFromDatabase(int mp_chordPosition, boolean history) throws IndexOutOfBoundsException {
        DBaccess dBaccess = App.getdBaccess();
        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
        Akkoord gekozenAkkoord = null;
        dBaccess.openConnection();
        try {
            if (akkoordKeuzeDAO.pullAllChords().size() > 0 && !history) {
                gekozenAkkoord =
                        akkoordKeuzeDAO.pullAllChords().get(akkoordKeuzeDAO.pullAllChords().size() - mp_chordPosition);
            } else if (akkoordKeuzeDAO.pullHistoryChords().size() > 0 && history) {
                gekozenAkkoord =
                        akkoordKeuzeDAO.pullHistoryChords().get(akkoordKeuzeDAO.pullHistoryChords().size() - mp_chordPosition);
            }
            else {
                System.out.println("No chord history yet");
            }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("Query is beyond the scope of the database");
        }
        dBaccess.closeConnection();
        return gekozenAkkoord;
    }

    // Test purposes.. can delete TODO
    private void printArrayList(ArrayList<String> arrayList) {
        for (String s : arrayList) {
            System.out.println(s);
        }
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
    public static String[] getToonsoorten() {
        return toonsoorten;
    }

    public void setToon(String toon) {
        this.toon = toon;
    }

    public void setToonsoort(String toonsoort) {
        this.toonsoort = toonsoort;
    }
}


