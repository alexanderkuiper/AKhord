package nl.miwgroningen.se.ch9.alex.database;

import nl.miwgroningen.se.ch9.alex.model.Akkoord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Schrijft de akkoordkeuzes weg naar de database
 */
public class AkkoordKeuzeDAO extends AbstractDAO {

    public AkkoordKeuzeDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaAkkoordKeuzeOp(Akkoord akkoord) {
        String sql = "INSERT INTO userchord (toon, toonsoort) VALUES (?, ?);";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, akkoord.getToon());
            preparedStatement.setString(2, akkoord.getToonsoort());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public ArrayList<Akkoord> pullAllChords() {
        String sql = "SELECT toon, toonsoort FROM userchord;";
        return pullChords(sql);
    }

    public ArrayList<Akkoord> pullHistoryChords() {
        String sql = "SELECT DISTINCT toon, toonsoort FROM userchord ORDER BY id DESC;";
        return pullChords(sql);
    }


    public ArrayList<Akkoord> pullChords(String sql) {
        ArrayList<Akkoord> akkoorden = new ArrayList<>();

        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
            akkoorden.add(getAkkoordFromResultSet(resultSet));
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        return akkoorden;
    }

    private Akkoord getAkkoordFromResultSet(ResultSet resultSet) throws SQLException {
        return new Akkoord(
                resultSet.getString(1),
                resultSet.getString(2));
    }
}
