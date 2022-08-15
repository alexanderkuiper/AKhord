package nl.miwgroningen.se.ch9.alex.controller;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Klasse voor akkoord: een toon en toonsoort.
 */
public class Akkoord {
    private String toon;
    private String toonsoort;

    public Akkoord(String toon, String toonsoort) {
        this.toon = toon;
        this.toonsoort = toonsoort;
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
}
