package nl.miwgroningen.se.ch9.alex.controller;

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
//        int[] gString = {5, 9, 0};
//        int[] cString = {0, 4, 7};
//        int[] eString = {8, 0, 3};
//        int[] aString = {3, 7, 10};

        List<List<Integer>> allStrings = new ArrayList<>();
        allStrings.add(List.of(5, 9, 0));
        allStrings.add(List.of(0, 4, 7));
        allStrings.add(List.of(8, 0, 3));
        allStrings.add(List.of(3, 7, 10));
        List<Integer[]> results = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        generateChordsList(allStrings, results, 0, current);
        for (Integer[] result : results) {
            System.out.println(Arrays.toString(result));
        }

        List<List<String>> allStringsString = new ArrayList<>();
        allStringsString.add(List.of("5", "9", "0"));
        allStringsString.add(List.of("0", "4", "7"));
        allStringsString.add(List.of("8", "0", "3"));
        allStringsString.add(List.of("3", "7", "10"));
        List<String> resultString = new ArrayList<>();
        String currentString = "";

//        generatePermutationsString(allStringsString, resultString, 0, currentString);
//        System.out.println(Arrays.toString(resultString.toArray()));
    }

    public static void generateChordsList(List<List<Integer>> lists, List<Integer[]> result, int depth, List<Integer> current) {
        if (depth == lists.size()) {
            result.add(current.toArray(new Integer[0]));

            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generateChordsList(lists, result, depth + 1, submitCurrent(lists, current, depth, i));
        }
    }

    public static List<Integer> submitCurrent(List<List<Integer>> lists, List<Integer> current, int depth, int i) {
        List<Integer> currentCopy = current;
        currentCopy.add(lists.get(depth).get(i));
        return currentCopy;
    }


    public static void generateChordsString(List<List<String>> lists, List<String> result, int depth, String current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generateChordsString(lists, result, depth + 1, current + lists.get(depth).get(i));
        }
    }


    }


