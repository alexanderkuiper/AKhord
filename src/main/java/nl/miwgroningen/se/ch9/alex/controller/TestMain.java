package nl.miwgroningen.se.ch9.alex.controller;

import nl.miwgroningen.se.ch9.alex.database.AkkoordKeuzeDAO;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;
import nl.miwgroningen.se.ch9.alex.model.CircularLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Tests the calculation of a chord using Circular Linked List.
 */
public class TestMain {
    public static void main(String[] args) {
        // Test circular linked list
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
//        tonenLijst.setHead(2);
//        tonenLijst.display();

        // Test interact with database
//        DBaccess dBaccess = new DBaccess("akkoord", "user", "password");
//        AkkoordKeuzeDAO akkoordKeuzeDAO = new AkkoordKeuzeDAO(dBaccess);
//        dBaccess.openConnection();

//         Test write chord to database
//        Akkoord akkoord = new Akkoord("B", "Mineur");
//        akkoordKeuzeDAO.slaAkkoordKeuzeOp(akkoord);
//
//        // Test pull chord from database
//        System.out.println(akkoordKeuzeDAO.toonAkkoorden());
//
//        dBaccess.closeConnection();

        // test creating all possible combinations of 4 arrays (1 per string) containing 3 notes of a chord
        int[] gString = {5, 9, 0};
        int[] cString = {0, 4, 7};
        int[] eString = {8, 0, 3};
        int[] aString = {3, 7, 10};

        List<List<Integer>> allStrings = new ArrayList<>();
        allStrings.add(List.of(5, 9, 0));
        allStrings.add(List.of(0, 4, 7));
        allStrings.add(List.of(8, 0, 3));
        allStrings.add(List.of(3, 7, 10));
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generatePermutations(allStrings, result, 0, current);

//        System.out.println(Arrays.toString(allStrings.toArray()));
        System.out.println(Arrays.toString(result.toArray()));


        List<Integer> chordSuggestions = new ArrayList<>();
//            // test print source arrays
//        System.out.println(Arrays.deepToString(allStrings));
//        System.out.println(Arrays.toString(allStrings[0]));
//        System.out.println(allStrings[0][1]);
//             test print result arrays
//        System.out.println(Arrays.deepToString(chordSuggestions.toArray()));
//        for (int i = 0; i < allStrings.length; i++) {
//            for (int j = 0; j < allStrings[i].length; j++) {
//            }
//        List<Integer[]> allStrings = new ArrayList<>();
//        allStrings.add(gString);
//        allStrings.add(cString);
//        allStrings.add(eString);
//        allStrings.add(aString);
        }

    public static void generatePermutations(List<List<Integer>> lists, List<List<Integer>> result, int depth, List<Integer> current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            current.add(lists.get(depth).get(i));
            generatePermutations(lists, result, depth + 1, current);
        }
    }




    }


