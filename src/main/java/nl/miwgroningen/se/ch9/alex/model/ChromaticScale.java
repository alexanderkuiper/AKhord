package nl.miwgroningen.se.ch9.alex.model;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Class describing the chromatic scale (all tones in an octave).
 */
public class ChromaticScale {
    private static final String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    private String note;
    private int index;
    private CircularLinkedList notesList;


    public ChromaticScale(String note) {
        this.note = note;
        this.notesList = new CircularLinkedList();
        convertChosenNoteToIndex();
        fillListWithNotes();
        setScaleRoot();
    }

    private void convertChosenNoteToIndex() {
        index = 0;
        for (int i = 0; i < notes.length; i++) {
            if (note.equals(notes[i])) {
                index = i + 1;
            }
        }
    }

    private void fillListWithNotes() {
        for (String note : notes) {
            notesList.addNode(note);
        }
    }

    private void setScaleRoot() {
        convertChosenNoteToIndex();
        notesList.setHead(index);
    }

    public static String[] getNotes() {
        return notes;
    }

    public CircularLinkedList getNotesList() {
        return notesList;
    }
}
