package nl.miwgroningen.se.ch9.alex.controller;

import nl.miwgroningen.se.ch9.alex.database.AkkoordKeuzeDAO;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Tests the calculation of a chord using Circular Linked List.
 */
public class TestMain {
    public static void main(String[] args) {
//        // Test circular linked list
//        CircularLinkedList tonenLijst = new CircularLinkedList();
//        String[] tonenArray = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
//        for (String toon : tonenArray) {
//            tonenLijst.addNode(toon);
//        }
//        System.out.println("Tonen array: ");
//        for (String toon : tonenArray) {
//            System.out.print(toon + ", ");
//        }
//
//        tonenLijst.setHead(12);
//        tonenLijst.display();

        // Test interact with database
        DBaccess dBaccess = new DBaccess("akkoord", "user", "password");
        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
        dBaccess.openConnection();

//         Test write chord to database
        Akkoord akkoord = new Akkoord("B", "Mineur");
        akkoordKeuzeDAO.slaAkkoordKeuzeOp(akkoord);

        // Test pull chord from database
        System.out.println(akkoordKeuzeDAO.toonAkkoorden());

        dBaccess.closeConnection();
    }

}
